package com.itbird.design.iterator.usersystem.iterator;

import java.util.List;

/**
 * list的迭代器
 * Created by itbird on 2022/7/7
 */
public class ListIterator<T> implements Iterator<T> {
    List<T> list;
    int currentIndex = 0;

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public T next() {
        return list.get(currentIndex++);
    }

    @Override
    public boolean hasNext() {
        return currentIndex < list.size();
    }
}
