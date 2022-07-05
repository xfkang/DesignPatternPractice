package com.itbird.design.proxy.demo.dynamic.v2;


import android.content.Context;

import leo.android.cglib.proxy.Enhancer;

/**
 * Created by itbird on 2022/7/4
 */
public class Client {
    public void main(Context context) {
        //Enhancer类是CGLib中的一个字节码增强器
        Enhancer enhancer = new Enhancer(context);
        //将被代理类TargetObject设置成父类，然后设置拦截器TargetInterceptor
        enhancer.setSuperclass(SourceObject.class);
        enhancer.setInterceptor(new ProxyMethodInterceptor());

        //执行enhancer.create()动态生成一个代理类，并从Object强制转型成父类型TargetObject
        SourceObject object = (SourceObject) enhancer.create();
        //在代理类上调用方法
        object.methodA();
    }
}
