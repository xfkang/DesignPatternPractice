package com.itbird.design.iterator.demo.v3.iterator;


import com.itbird.design.iterator.demo.v3.WordInfo;

/**
 * Created by itbird on 2022/7/9
 */
public class NodeIterator<T> implements Iterator<T> {
    WordInfo.Node node;

    public void setNode(WordInfo.Node node) {
        this.node = node;
    }

    @Override
    public T next() {
        return (T) node.getNext();
    }

    @Override
    public boolean hasNext() {
        return node.getNext() != null;
    }
}
