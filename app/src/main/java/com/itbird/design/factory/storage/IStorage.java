package com.itbird.design.factory.storage;

/**
 * 存储相关接口
 * Created by itbird on 2022/6/1
 */
public interface IStorage {
    /*************save data start***************/
    void save(String key, String value);

    void save(String key, double value);

    void save(String key, int value);

    void save(String key, long value);

    void save(String key, boolean value);

    void save(String key, Object value);
    /*************end data start***************/

    /*************get data start***************/
    String getString(String key);

    double getDouble(String key, double defaultValue);

    int getInt(String key, int defaultValue);

    long getLong(String key, long defaultValue);

    boolean getBoolean(String key, boolean defaultValue);

    Object getObject(String key);
    /*************get data end***************/
}
