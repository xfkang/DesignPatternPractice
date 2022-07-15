package com.itbird.design.facade.demo;

/**
 * 门面，系统的对外调用统一对象，里面调用各个子系统
 * Created by itbird on 2022/7/12
 */
public class Facade {
    SystemA systemA;
    SystemB systemB;
    SystemC systemC;

    public Facade() {
        systemA = new SystemA();
        systemB = new SystemB();
        systemC = new SystemC();
    }

    public void method() {
        systemA.method();
        systemB.method();
        systemC.method();
    }
}
