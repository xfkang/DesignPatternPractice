package com.itbird.design.decorator.recycleview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 自定义WrapperRecycleView，重写方法setAdapter，用于封装new WrapperRecyclerAdapter的操作
 * Created by itbird on 2022/6/10
 */
public class WrapperRecycleView extends RecyclerView {
    WrapperRecyclerAdapter wrapperRecyclerAdapter;

    public WrapperRecycleView(@NonNull Context context) {
        super(context);
    }

    public WrapperRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapperRecycleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        wrapperRecyclerAdapter = new WrapperRecyclerAdapter(adapter);
        super.setAdapter(wrapperRecyclerAdapter);
    }

    @Nullable
    @Override
    public Adapter getAdapter() {
        return wrapperRecyclerAdapter;
    }

    public void addHeadView(View view) {
        wrapperRecyclerAdapter.addHeadView(view);
    }

    public void addFootView(View view) {
        wrapperRecyclerAdapter.addFootView(view);
    }

    public void removeHeadView(View view) {
        wrapperRecyclerAdapter.removeHeadView(view);
    }

    public void removeFootView(View view) {
        wrapperRecyclerAdapter.removeFootView(view);
    }
}
