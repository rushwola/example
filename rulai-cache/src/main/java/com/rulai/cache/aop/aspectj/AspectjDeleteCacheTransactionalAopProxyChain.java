package com.rulai.cache.aop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;

import com.rulai.cache.aop.DeleteCacheTransactionalAopProxyChain;

public class AspectjDeleteCacheTransactionalAopProxyChain implements DeleteCacheTransactionalAopProxyChain {

    private final ProceedingJoinPoint jp;

    public AspectjDeleteCacheTransactionalAopProxyChain(ProceedingJoinPoint jp) {
        this.jp=jp;

    }

    @Override
    public Object doProxyChain() throws Throwable {
        return jp.proceed();
    }

}
