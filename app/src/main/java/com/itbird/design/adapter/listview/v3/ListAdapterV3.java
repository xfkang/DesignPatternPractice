package com.itbird.design.adapter.listview.v3;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.itbird.design.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象适配器实现方式
 * 1.实现适配器接口
 * 2.持有想要适配的对象，即Adaptee，在这里的话，是指数据
 * 3.添加数据监听
 * Created by itbird on 2022/6/29
 */
public class ListAdapterV3 implements BaseAdapterV3 {
    List<String> data;
    Context mContext;
    DataSetObservable dataSetObservable = new DataSetObservable();
    public ListAdapterV3(Context context) {
        mContext = context;
    }

    /**
     * 注册观察者
     *
     * @param observer
     */
    @Override
    public void registerObserver(DataSetObserver observer) {
        dataSetObservable.registerObserver(observer);
    }

    /**
     * 注销观察者
     *
     * @param observer
     */
    @Override
    public void unregisterObserver(DataSetObserver observer) {
        dataSetObservable.unregisterObserver(observer);
    }

    /**
     * 通知观察者
     */
    @Override
    public void notifySetChanged() {
        dataSetObservable.notifyChanged();
    }

    @Override
    public void unregisterAll() {
        dataSetObservable.unregisterAll();
    }

    /**
     * 设置数据
     *
     * @param data
     */
    public void setData(List<String> data) {
        this.data = data;
    }

    /**
     * 获取item数据
     *
     * @return
     */
    @Override
    public int getCount() {
        return data.size();
    }

    /**
     * 获取没item的view
     *
     * @param index
     * @return
     */
    @Override
    public View getItemView(int index) {
        TextView textView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_listview, null);
        textView.setText(data.get(index));
        return textView;
    }
}
