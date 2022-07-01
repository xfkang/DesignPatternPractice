package com.itbird.design.adapter.demo;

/**
 * 类适配器
 * 类适配器的作用：
 * Created by itbird on 2022/6/30
 */
public class ClassAdapter extends Adaptee implements Target {
    @Override
    public int output5V() {
        return get220VOutput() - 215;
    }
}
