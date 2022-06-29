package com.itbird.design.strategy.demo.compute.v2;

/**
 * Created by itbird on 2022/6/23
 */
public interface ICompute<T>{
    T compute(T... a );
}
