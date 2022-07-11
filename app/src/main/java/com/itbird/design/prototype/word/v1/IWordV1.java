package com.itbird.design.prototype.word.v1;

/**
 * 文档接口
 * Created by itbird on 2022/7/6
 */
public abstract class IWordV1<T, V> {
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

    public abstract IWordV1 copy();
}
