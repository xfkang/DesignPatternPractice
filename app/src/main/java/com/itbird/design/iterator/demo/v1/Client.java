package com.itbird.design.iterator.demo.v1;

import java.util.List;

/**
 * 客户端去使用Word文档内容
 * Created by itbird on 2022/7/9
 */
public class Client {
    public void main() {
        WordInfo info = new WordInfo();
        List<String>  list = info.getContent();
        for (int i = 0; i < list.size(); i++) {
            // todo 去使用content数据
        }
    }
}
