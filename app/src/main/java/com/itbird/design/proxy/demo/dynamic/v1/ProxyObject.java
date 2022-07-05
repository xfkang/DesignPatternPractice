package com.itbird.design.proxy.demo.dynamic.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理对象
 * Created by itbird on 2022/7/4
 */
public class ProxyObject implements InvocationHandler {
    Object proxy;

    public ProxyObject(Object proxy) {
        this.proxy = proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxy, args);
    }
}
