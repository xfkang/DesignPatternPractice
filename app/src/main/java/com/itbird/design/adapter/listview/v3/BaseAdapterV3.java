package com.itbird.design.adapter.listview.v3;

import android.database.DataSetObserver;
import android.view.View;

/**
 * 适配器接口，用于对listview提供的接口
 * Created by itbird on 2022/6/29
 */
public interface BaseAdapterV3 {
    /**
     * 获取item数量
     *
     * @return
     */
    int getCount();

    /**
     * 根据输入的string，穿件view
     */
    View getItemView(int index);

    /***
     *  注册观察者
     * @param observer
     */
    void registerObserver(DataSetObserver observer);

    /**
     * 注销观察者
     * @param observer
     */
    void unregisterObserver(DataSetObserver observer);

    /**
     * 通知观察者
     */
    void notifySetChanged();
    void unregisterAll();
}
