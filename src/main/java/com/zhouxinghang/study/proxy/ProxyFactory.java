package com.zhouxinghang.study.proxy;

import com.google.common.base.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;

/**
 * Created by zhouxinghang on 2018/5/23.
 */
public class ProxyFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ProxyFactory.class);
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * ClassLoader loader,
     * Class<?>[] interfaces,
     * InvocationHandler h
     * @return
     */
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),
            (proxy, method, args) -> {
                LOG.info("开始事务, proxy:{}, method:{}, argsCount:{}", proxy, method.getName(), args.length);
                Object result = method.invoke(target, args);
                LOG.info("结束事务,result:{}", result);
                return  result;
            });
    }
}
