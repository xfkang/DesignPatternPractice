package com.itbird.design.strategy.demo.compute.v2;

/**
 * ADD
 * Created by itbird on 2022/6/23
 */
public class ComputeADD implements ICompute<Double> {
    @Override
    public Double compute(Double... a) {
        double sum = 0;
        for (double k : a) {
            sum += k;
        }
        return sum;
    }
}
