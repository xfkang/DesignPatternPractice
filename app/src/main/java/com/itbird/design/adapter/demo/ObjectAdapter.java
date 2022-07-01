package com.itbird.design.adapter.demo;

/**
 * 对象适配器
 * Created by itbird on 2022/6/30
 */
public class ObjectAdapter implements Target {
    Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }


    @Override
    public int output5V() {
        return adaptee.get220VOutput() - 215;
    }
}
