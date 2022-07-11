package com.itbird.design.iterator.usersystem.iterator;

/**
 * 数组的迭代器
 * Created by itbird on 2022/7/7
 */
public class ArrayIterator<T> implements Iterator<T> {
    T[] list;
    int currentIndex = 0;

    public void setList(T[] list) {
        this.list = list;
    }

    @Override
    public T next() {
        return list[currentIndex++];
    }

    @Override
    public boolean hasNext() {
        int a = list.length;
        return currentIndex < a;
    }
}
