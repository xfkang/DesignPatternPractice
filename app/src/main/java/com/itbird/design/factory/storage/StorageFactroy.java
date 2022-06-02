package com.itbird.design.factory.storage;

/**
 * 存储相关的工厂接口
 * Created by itbird on 2022/6/1
 */
public class StorageFactroy implements IStorageFactroy {
    private StorageFactroy() {
    }

    public static StorageFactroy getInstance() {
        return StorageFactroyHolder.instance;
    }

    private final static class StorageFactroyHolder {
        final static StorageFactroy instance = new StorageFactroy();
    }

    @Override
    public <T extends IStorage> T getStorageHandler(Class<T> tClass) {
        Class cls = null;
        try {
            cls = Class.forName(tClass.getCanonicalName());
            return (T) cls.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
