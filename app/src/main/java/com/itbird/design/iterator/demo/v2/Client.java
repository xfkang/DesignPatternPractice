package com.itbird.design.iterator.demo.v2;

/**
 * 客户端去使用Word文档内容
 * Created by itbird on 2022/7/9
 */
public class Client {
    public void main() {
        WordInfo info = new WordInfo();
        WordInfo.Node node = info.getNode();
        while (node != null) {
            // todo 去使用content数据
            node = node.next;
        }
    }
}
