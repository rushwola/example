package com.rulai.cache.aop;

import java.lang.reflect.Method;

public interface DeleteCacheAopProxyChain {

    Object[] getArgs();

    @SuppressWarnings("rawtypes")
    Class getTargetClass();

    Method getMethod();

}
