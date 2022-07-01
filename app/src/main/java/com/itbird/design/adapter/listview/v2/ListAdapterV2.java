package com.itbird.design.adapter.listview.v2;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.itbird.design.R;

import java.util.List;

/**
 * 对象适配器实现方式
 * 1.实现适配器接口
 * 2.持有想要适配的对象，即Adaptee，在这里的话，是指数据
 * Created by itbird on 2022/6/29
 */
public class ListAdapterV2 implements BaseAdapterV2 {
    List<String> data;
    Context mContext;

    public ListAdapterV2(Context context) {
        mContext = context;
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
