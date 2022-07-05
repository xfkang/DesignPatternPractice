package com.itbird.design.proxy.demo.dynamic.v2;

import leo.android.cglib.proxy.MethodInterceptor;
import leo.android.cglib.proxy.MethodProxy;

/**
 * 代理对象
 * Created by itbird on 2022/7/4
 */
public class ProxyMethodInterceptor implements MethodInterceptor {

    /**
     * 重写方法拦截在方法前和方法后加入业务
     * Object obj为目标对象
     * Method method为目标方法
     * Object[] params 为参数，
     * MethodProxy proxy CGlib方法代理对象
     */
    @Override
    public Object intercept(Object o, Object[] objects, MethodProxy methodProxy) throws Exception {
        //参数：Object为由CGLib动态生成的代理类实例，Method为上文中实体类所调用的被代理的方法引用，Object[]为参数值列表，MethodProxy为生成的代理类对方法的代理引用。
        //返回：从代理实例的方法调用返回的值。
        //其中，proxy.invokeSuper(obj,arg) 调用代理类实例上的proxy方法的父类方法（即实体类TargetObject中对应的方法）
        System.out.println("调用前");
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println(" 调用后"+result);
        return result;
    }
}
