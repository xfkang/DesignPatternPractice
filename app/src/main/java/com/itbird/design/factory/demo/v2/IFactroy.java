package com.itbird.design.factory.demo.v2;

import com.itbird.design.factory.demo.v1.IProduct;

/**
 * 工厂接口
 * Created by itbird on 2022/6/1
 */
public interface IFactroy {
    <T extends IProduct> T createProduct(Class<T> tClass);
}
