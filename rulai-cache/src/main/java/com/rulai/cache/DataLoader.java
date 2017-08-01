package com.rulai.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rulai.cache.annotation.Cache;
import com.rulai.cache.aop.CacheAopProxyChain;
import com.rulai.cache.exception.LoadDataTimeOutException;
import com.rulai.cache.lock.ILock;
import com.rulai.cache.to.AutoLoadConfig;
import com.rulai.cache.to.AutoLoadTO;
import com.rulai.cache.to.CacheKeyTO;
import com.rulai.cache.to.CacheWrapper;
import com.rulai.cache.to.ProcessingTO;

/**
 * 数据加载器
 * @author jiayu.qiu
 */
public class DataLoader {

    private static final Logger logger=LoggerFactory.getLogger(DataLoader.class);

    private CacheHandler cacheHandler;

    private CacheAopProxyChain pjp;

    private CacheKeyTO cacheKey;

    private Cache cache;

    private Object[] arguments;

    private AutoLoadTO autoLoadTO;

    private boolean isFirst=true;

    private long loadDataUseTime;

    private CacheWrapper<Object> cacheWrapper;

    private int tryCnt=0;

    public DataLoader() {

    }

    public DataLoader init(CacheAopProxyChain pjp, AutoLoadTO autoLoadTO, CacheKeyTO cacheKey, Cache cache, CacheHandler cacheHandler) {
        this.cacheHandler=cacheHandler;
        this.pjp=pjp;
        this.cacheKey=cacheKey;
        this.cache=cache;
        this.autoLoadTO=autoLoadTO;
        if(null == autoLoadTO) {// 用户请求
            this.arguments=pjp.getArgs();
        } else {// 来自AutoLoadHandler的请求
            this.arguments=autoLoadTO.getArgs();
        }
        this.isFirst=true;
        this.loadDataUseTime=0;
        this.tryCnt=0;
        return this;
    }

    public DataLoader init(CacheAopProxyChain pjp, CacheKeyTO cacheKey, Cache cache, CacheHandler cacheHandler, Object[] arguments) {
        this.cacheHandler=cacheHandler;
        this.pjp=pjp;
        this.cacheKey=cacheKey;
        this.cache=cache;
        this.autoLoadTO=null;
        this.arguments=arguments;
        this.isFirst=true;
        this.loadDataUseTime=0;
        this.tryCnt=0;
        return this;
    }

    public DataLoader init(CacheAopProxyChain pjp, Cache cache, CacheHandler cacheHandler) {
        return init(pjp, null, null, cache, cacheHandler);
    }

    public DataLoader init(CacheAopProxyChain pjp, CacheKeyTO cacheKey, Cache cache, CacheHandler cacheHandler) {
        return init(pjp, null, cacheKey, cache, cacheHandler);
    }

    public DataLoader loadData() throws Throwable {
        ProcessingTO processing=cacheHandler.processing.get(cacheKey);
        ProcessingTO processingTO=null;
        if(null == processing) {
            processingTO=new ProcessingTO();
            ProcessingTO _processing=cacheHandler.processing.putIfAbsent(cacheKey, processingTO);// 为发减少数据层的并发，增加等待机制。
            if(null != _processing) {
                processing=_processing;// 获取到第一个线程的ProcessingTO 的引用，保证所有请求都指向同一个引用
            }
        }
        Object lock=null;
        String tname=Thread.currentThread().getName();
        if(null == processing) {// 当前并发中的第一个请求
            isFirst=true;
            lock=processingTO;
            logger.debug(tname + " first thread!");
            try {
                doFirstRequest(processingTO);
            } catch(Throwable e) {
                processingTO.setError(e);
                throw e;
            } finally {
                processingTO.setFirstFinished(true);
                cacheHandler.processing.remove(cacheKey);
                synchronized(lock) {
                    lock.notifyAll();
                }
            }
        } else {
            isFirst=false;
            lock=processing;
            doWaitRequest(processing, lock);
        }
        return this;
    }

    private void doFirstRequest(ProcessingTO processingTO) throws Throwable {
        ILock distributedLock=cacheHandler.getLock();
        if(null != distributedLock && cache.lockExpire() > 0) {// 开启分布式锁
            String lockKey=cacheKey.getLockKey();
            long startWait=processingTO.getStartTime();
            do {
                if(distributedLock.tryLock(lockKey, cache.lockExpire())) {// 获得分布式锁
                    try {
                        getData();
                    } finally {
                        distributedLock.unlock(lockKey);
                    }
                    break;
                }
                for(int i=0; i < 10; i++) {// 没有获得锁时，定时缓存尝试获取数据
                    cacheWrapper=cacheHandler.get(cacheKey, pjp.getMethod(), this.arguments);
                    if(null != cacheWrapper) {
                        break;
                    }
                    Thread.sleep(20);
                }
                if(null != cacheWrapper) {
                    break;
                }
            } while(System.currentTimeMillis() - startWait < cache.waitTimeOut());
            if(null == cacheWrapper) {
                throw new LoadDataTimeOutException("load data for key \"" + cacheKey.getCacheKey() + "\" timeout(" + cache.waitTimeOut() + " ms).");
            }
        } else {
            getData();
        }
        processingTO.setCache(cacheWrapper);// 本地缓存
    }

    private void doWaitRequest(ProcessingTO processing, Object lock) throws Throwable {
        long startWait=processing.getStartTime();
        String tname=Thread.currentThread().getName();
        do {// 等待
            if(null == processing) {
                break;
            }
            if(processing.isFirstFinished()) {
                CacheWrapper<Object> _cacheWrapper=processing.getCache();// 从本地缓存获取数据， 防止频繁去缓存服务器取数据，造成缓存服务器压力过大
                logger.debug(tname + " do FirstFinished" + " is null :" + (null == _cacheWrapper));
                if(null != _cacheWrapper) {
                    cacheWrapper=_cacheWrapper;
                    return;
                }
                Throwable error=processing.getError();
                if(null != error) {// 当DAO出错时，直接抛异常
                    logger.debug(tname + " do error");
                    throw error;
                }
                break;
            } else {
                synchronized(lock) {
                    logger.debug(tname + " do wait");
                    try {
                        lock.wait(10);// 如果要测试lock对象是否有效，wait时间去掉就可以
                    } catch(InterruptedException ex) {
                        logger.error(ex.getMessage(), ex);
                    }
                }
            }
        } while(System.currentTimeMillis() - startWait < cache.waitTimeOut());
        if(null == cacheWrapper) {
            cacheWrapper=cacheHandler.get(cacheKey, pjp.getMethod(), this.arguments);
        }
        if(null == cacheWrapper) {
            AutoLoadConfig config=cacheHandler.getAutoLoadConfig();
            if(tryCnt < config.getLoadDataTryCnt()) {
                tryCnt++;
                loadData();
            } else {
                throw new LoadDataTimeOutException("cache for key \"" + cacheKey.getCacheKey() + "\" loaded " + tryCnt + " times.");
            }
        }
    }

    public boolean isFirst() {
        return isFirst;
    }

    public DataLoader getData() throws Throwable {
        try {
            if(null != autoLoadTO) {
                autoLoadTO.setLoading(true);
            }
            long loadDataStartTime=System.currentTimeMillis();
            Object result=pjp.doProxyChain(arguments);
            loadDataUseTime=System.currentTimeMillis() - loadDataStartTime;
            AutoLoadConfig config=cacheHandler.getAutoLoadConfig();
            if(config.isPrintSlowLog() && loadDataUseTime >= config.getSlowLoadTime()) {
                String className=pjp.getTargetClass().getName();
                logger.error(className + "." + pjp.getMethod().getName() + ", use time:" + loadDataUseTime + "ms");
            }
            buildCacheWrapper(result);
        } catch(Throwable e) {
            throw e;
        } finally {
            if(null != autoLoadTO) {
                autoLoadTO.setLoading(false);
            }
        }
        return this;
    }

    private void buildCacheWrapper(Object result) {
        int expire=cache.expire();
        try {
            expire=cacheHandler.getScriptParser().getRealExpire(cache.expire(), cache.expireExpression(), arguments, result);
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
        }
        cacheWrapper=new CacheWrapper<Object>(result, expire);
    }

    public CacheWrapper<Object> getCacheWrapper() {
        if(null == cacheWrapper) {
            throw new RuntimeException("run loadData() or buildCacheWrapper() please!");
        }
        return cacheWrapper;
    }

    public long getLoadDataUseTime() {
        return loadDataUseTime;
    }

}
