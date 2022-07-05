package com.itbird.design.proxy.demo.staticc;

/**
 * 代理对象，只开放源对象希望开放的方法
 * Created by itbird on 2022/7/4
 */
public class ProxyObject implements IObject{
    private IObject mSourceObject;

    public ProxyObject(IObject sourceObject) {
        mSourceObject = sourceObject;
    }

    public void methodA() {
        mSourceObject.methodA();
    }

    @Override
    public void methodB() {
        mSourceObject.methodB();
    }

    @Override
    public void methodC() {
        mSourceObject.methodC();
    }

    @Override
    public void methodD() {
        mSourceObject.methodD();
    }

    @Override
    public void methodE() {
        mSourceObject.methodE();
    }
}
