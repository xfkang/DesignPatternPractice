package com.itbird.design.prototype.word.v2;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文档数据v2，通过clone实现数据copy
 * Created by itbird on 2022/7/6
 */
public class WordV2DataInfo extends IWordV2<List, String> {
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
        builder.append("当前的文档内容为：" + list);
        builder.append("当前的list的内存地址为：" + System.identityHashCode(list));
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

    public WordV2DataInfo clone() {
        WordV2DataInfo info = null;
        try {
            info = (WordV2DataInfo) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return info;
    }
}
