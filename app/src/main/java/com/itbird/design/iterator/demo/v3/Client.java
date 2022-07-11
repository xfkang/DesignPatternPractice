package com.itbird.design.iterator.demo.v3;

import com.itbird.design.iterator.demo.v3.iterator.Iterator;

/**
 * 客户端去使用Word文档内容
 * Created by itbird on 2022/7/9
 */
public class Client {
    public void main() {
        WordInfo wordInfo = new WordInfo();
        Iterator<WordInfo.Node> iterator = (Iterator<WordInfo.Node>) wordInfo.getNodeNodeIterator();
        while (iterator.hasNext()) {
            // todo 去使用content数据
            WordInfo.Node node = iterator.next();
            node = node.next;
        }
    }
}
