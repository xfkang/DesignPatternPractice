package com.itbird.design.strategy.demo.compute.v1;

/**
 * 加减乘除运算
 * Created by itbird on 2022/6/23
 */
public class Compute {
    /**
     * 这里看到，加减乘除实际都是对于数字的操作而已，只不过操作不同而已
     * 这里这种方式实现，有以下弊端
     * 1）每次新增操作运算，需要新增type、修改这个compute类，带来风险
     * 2）每次新增操作运算，需要新增if判断，导致这个类越来越重
     * @param type
     * @param a
     * @param b
     * @return
     */
    public static double compute(int type, double a, double b) {
        if (type == 1) {
            return a + b;
        } else if (type == 2) {
            return a - b;
        } else if (type == 3) {
            return a * b;
        } else if (type == 4) {
            if (b == 0) {
                return -1;
            }
            return a / b;
        } else {
            return -1;
        }
    }
}
