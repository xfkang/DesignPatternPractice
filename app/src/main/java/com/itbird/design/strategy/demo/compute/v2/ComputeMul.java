package com.itbird.design.strategy.demo.compute.v2;

/**
 * multiplication
 * Created by itbird on 2022/6/23
 */
public class ComputeMul implements ICompute<Double> {
    @Override
    public Double compute(Double... a) {
        double sum = 1;
        for (int i = 0; i < a.length; i++) {
            sum *= a[i];
        }
        return sum;
    }
}
