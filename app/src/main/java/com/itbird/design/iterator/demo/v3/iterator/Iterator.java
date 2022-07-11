package com.itbird.design.iterator.demo.v3.iterator;

/**
 * Created by itbird on 2022/7/9
 */
public interface Iterator<T> {
    T next();

    boolean hasNext();
}
