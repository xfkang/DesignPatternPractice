package com.itbird.design.template.demo;

/**
 * 抽象类，定义算法框架和执行步骤
 * Created by itbird on 2022/6/16
 */
public abstract class AbsTemplate {
    /**
     * execute一般声明为final，防止子类恶意修改执行步骤
     */
    final public void execute() {
        step1();
        step2();
        step3();
    }

    /**
     * 具体的算法细节交于子类去实现
     */
    abstract void step1();

    abstract void step2();

    abstract void step3();
}
