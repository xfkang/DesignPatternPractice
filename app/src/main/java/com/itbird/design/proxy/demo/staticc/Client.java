package com.itbird.design.proxy.demo.staticc;


/**
 * Created by itbird on 2022/7/4
 */
public class Client {
    public void main() {
        //被代理对象
        SourceObject sourceObject = new SourceObject();

        //代理对象
        ProxyObject proxyObject = new ProxyObject(sourceObject);
        proxyObject.methodA();
    }
}
