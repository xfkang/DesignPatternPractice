package com.itbird.design.proxy.demo.dynamic.v1;

import java.lang.reflect.Proxy;

/**
 * Created by itbird on 2022/7/4
 */
public class Client {
    public void main() {
        SourceObject sourceObject = new SourceObject();
        // 返回的是 IBank 的一个实例对象，这个对象是由 Java 给我们创建的 ,调用的是 jni，通过反射+classloader加载
        IObject proxy = (IObject) Proxy.newProxyInstance(IObject.class.getClassLoader(), // ClassLoader
                new Class[]{IObject.class},  //目标接口
                new ProxyObject(sourceObject)); //替换代理
        proxy.methodA();
    }
}
