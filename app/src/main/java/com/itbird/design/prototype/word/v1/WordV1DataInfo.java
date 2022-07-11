package com.itbird.design.prototype.word.v1;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文档数据
 * Created by itbird on 2022/7/6
 */
public class WordV1DataInfo extends IWordV1<List, String> {
    /**
     * 文档标题
     */
    String name;

    /**
     * 文档内容，我们这里以list去存储
     */
    List<String> list = new ArrayList<>();


    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("当前的文档标题为" + name);
        builder.append("当前的文档内容为：" + Arrays.toString(list.toArray()));
        return builder.toString();
    }

    @Override
    public void addContent(String string) {
        list.add(string);
    }

    @Override
    public void removeContent(String string) {
        list.remove(string);
    }

    @Override
    public void setTitle(String name) {
        this.name = name;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public List getContent() {
        return list;
    }

    @Override
    public WordV1DataInfo copy() {
        WordV1DataInfo wordDataInfo = new WordV1DataInfo();
        wordDataInfo.name = name;
        wordDataInfo.list = list;
        return wordDataInfo;
    }
}
