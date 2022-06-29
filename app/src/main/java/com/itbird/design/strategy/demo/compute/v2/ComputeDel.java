package com.itbird.design.strategy.demo.compute.v2;

/**
 * del
 * Created by itbird on 2022/6/23
 */
public class ComputeDel implements ICompute<Double> {
    @Override
    public Double compute(Double... a) {
        double sum = a[0];
        for (int i = 1; i < a.length; i++) {
            sum -= a[i];
        }
        return sum;
    }
}
