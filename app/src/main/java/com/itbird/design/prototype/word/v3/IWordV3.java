package com.itbird.design.prototype.word.v3;

/**
 * v3通过Cloneable实现数据copy
 * 文档接口
 * Created by itbird on 2022/7/6
 */
public abstract class IWordV3<T, V> implements Cloneable{
    /**
     * 追加内容
     *
     * @param string
     */
    public abstract void addContent(V string);

    /**
     * 移除内容
     *
     * @param string
     */
    public abstract void removeContent(V string);

    /**
     * 设置标题
     *
     * @param name
     */
    public abstract void setTitle(V name);

    public abstract V getTitle();

    public abstract T getContent();
}
