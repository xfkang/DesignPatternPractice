package com.itbird.design.principle.imageloader.v3;

/**
 * 缓存功能接口
 * Created by itbird on 2022/3/28
 */
public interface ICache<T> {
    /**
     * 初始化缓存
     */
    public void init();

    /**
     * 获取缓存
     *
     * @param url
     * @return
     */
    public T getCache(String url);

    /**
     * 添加缓存
     *
     * @param url
     * @param bitmap
     */
    public void putCache(String url, T bitmap);

    /**
     * 移除缓存
     *
     * @param url
     */
    void removeCache(String url);
}
