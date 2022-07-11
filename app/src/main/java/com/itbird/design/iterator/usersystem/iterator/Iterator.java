package com.itbird.design.iterator.usersystem.iterator;

/**
 * 迭代接口定义
 * Created by itbird on 2022/7/7
 */
public interface Iterator<T> {
    /**
     * 下一个元素
     */
    T next();
    /**
     * 是否有下一个元素
     */
    boolean hasNext();
}
