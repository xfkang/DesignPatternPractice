package com.itbird.design.iterator.demo.v1;

import java.util.List;

/**
 * word文档内容
 * Created by itbird on 2022/7/9
 */
public class WordInfo {
    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    List<String> content;
}
