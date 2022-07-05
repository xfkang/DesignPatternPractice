package com.itbird.design.proxy.demo.dynamic.v1;

/**
 * 抽象主题类
 * 该类的主要职责是，负责声明真实主题与代理的共同的接口方法，该类既可以是一个接口，也可以是一个抽象
 * Created by itbird on 2022/7/4
 */
interface IObject {
    void methodA();

    void methodB();

    void methodC();

    void methodD();

    void methodE();
}
