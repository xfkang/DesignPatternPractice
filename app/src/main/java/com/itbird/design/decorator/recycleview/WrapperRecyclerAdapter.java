package com.itbird.design.decorator.recycleview;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView.Adapter包装类，扩展实现headView、footView的添加
 * Created by itbird on 2022/6/10
 */
public class WrapperRecyclerAdapter extends RecyclerView.Adapter {
    RecyclerView.Adapter adapter;
    List<View> headViews = new ArrayList<>();
    List<View> footViews = new ArrayList<>();

    public WrapperRecyclerAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        //头部的，返回头部的viewholder
        if (position < headViews.size()) {
            return new WrapperViewHolder(headViews.get(position));
        }
        //adapter返回中间数据holder
        if (position >= headViews.size() && position < headViews.size() + adapter.getItemCount()) {
            return adapter.onCreateViewHolder(parent, adapter.getItemViewType(position - headViews.size()));
        }

        //尾部的，返回尾部的viewholder
        return new WrapperViewHolder(footViews.get(position - headViews.size() - adapter.getItemCount()));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position < headViews.size() || position >= adapter.getItemCount() + headViews.size()) {
            return;
        }
        //头部和底部不需要做处理，只需要真实的adapter需要处理
        adapter.onBindViewHolder(holder, position - headViews.size());
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return headViews.size() + footViews.size() + adapter.getItemCount();
    }

    public void addHeadView(View view) {
        if (!headViews.contains(view)) {
            headViews.add(view);
            notifyDataSetChanged();
        }
    }

    public void addFootView(View view) {
        if (!footViews.contains(view)) {
            footViews.add(view);
            notifyDataSetChanged();
        }
    }

    public void removeHeadView(View view) {
        if (headViews.contains(view)) {
            headViews.add(view);
            notifyDataSetChanged();
        }
    }

    public void removeFootView(View view) {
        if (footViews.contains(view)) {
            footViews.remove(view);
            notifyDataSetChanged();
        }
    }

    static class WrapperViewHolder extends RecyclerView.ViewHolder {
        public WrapperViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
