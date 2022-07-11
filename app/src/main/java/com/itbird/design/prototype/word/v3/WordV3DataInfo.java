package com.itbird.design.prototype.word.v3;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 文档数据v3，通过clone实现数据c深克隆
 * Created by itbird on 2022/7/6
 */
public class WordV3DataInfo extends IWordV3<List, String> {
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

    public WordV3DataInfo clone() {
        WordV3DataInfo info = null;
        try {
            info = (WordV3DataInfo) super.clone();
            //这里由于List并未实现clone接口，所以无法直接调用clone方法
            //如果是自定义的对象，则可以去实现clone
            info.list = new ArrayList<>(list);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return info;
    }
}
