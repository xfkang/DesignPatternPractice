package com.itbird.design.factory.storage;

/**
 * 工厂接口
 * Created by itbird on 2022/6/1
 */
public interface IStorageFactroy {
    <T extends IStorage> T getStorageHandler(Class<T> tClass);
}
