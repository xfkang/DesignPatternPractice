package com.itbird.design.adapter.listview.v2;

import android.view.View;

/**
 * 适配器接口，用于对listview提供的接口
 * Created by itbird on 2022/6/29
 */
public interface BaseAdapterV2 {
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
}
