package com.itbird.design.strategy.demo.compute.v2;

/**
 * Context 是一个使用了某种策略的类
 * Created by itbird on 2022/6/23
 */
public class Context {
    ICompute<Double> iCompute;

    public Context(ICompute<Double> compute) {
        iCompute = compute;
    }

    public Double compute(Double... a) {
        return iCompute.compute(a);
    }

}
