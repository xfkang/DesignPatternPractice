package com.itbird.design.factory.demo.v1;

/**
 * 工厂接口
 * Created by itbird on 2022/6/1
 */
public interface IFactroy {
    IProduct createProduct(int type);
}
