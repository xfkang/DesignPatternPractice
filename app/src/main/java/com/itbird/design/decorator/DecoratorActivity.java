package com.itbird.design.decorator;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itbird.design.R;
import com.itbird.design.decorator.demo.Component;
import com.itbird.design.decorator.demo.ComponentImplA;
import com.itbird.design.decorator.demo.ConCreateComponent;
import com.itbird.design.decorator.recycleview.WrapperRecycleView;
import com.itbird.design.decorator.recycleview.WrapperRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试装饰者模式
 * Created by itbird on 2022/6/9
 */
public class DecoratorActivity extends AppCompatActivity {
    MyRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decorator);
        Component component = new ConCreateComponent();
        ComponentImplA impl1 = new ComponentImplA(component);
        impl1.operation();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("position " + i);
        }
        adapter = new MyRecycleViewAdapter(this);
        adapter.setData(list);
    }

    /**
     * 原始的yRecycleViewAdapter v1
     */
    public void buttonv1(View view) {
        findViewById(R.id.recycleview).setVisibility(View.VISIBLE);
        findViewById(R.id.wrapperR).setVisibility(View.GONE);

        RecyclerView recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    /**
     * 扩展的，可以增加头尾的recycleview v2
     */
    public void buttonv2(View view) {
        findViewById(R.id.recycleview).setVisibility(View.VISIBLE);
        findViewById(R.id.wrapperR).setVisibility(View.GONE);

        RecyclerView recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        WrapperRecyclerAdapter wrapperRecyclerAdapter = new WrapperRecyclerAdapter(adapter);
        //这里head为什么不会全屏，因为LayoutInflater需要parent才会全屏
        wrapperRecyclerAdapter.addHeadView(LayoutInflater.from(this).inflate(R.layout.layout_header_view, recyclerView, false));
        wrapperRecyclerAdapter.addFootView(new Button(this));
        recyclerView.setAdapter(wrapperRecyclerAdapter);
//        面向对象的六大基本原则，好像不符合最小知道原则，每次调用需要去new WrapperRecyclerAdapter这样的一个包装者，这肯定是不对的，所以需要封装自己的recycleview
    }

    /**
     * 将wrapperadapter的new操作，内部实现 v3
     * 封装的必要性，这样的话，只需要关注WrapperRecycleView，不再需要关注WrapperRecyclerAdapter
     */
    public void buttonv3(View view) {
        findViewById(R.id.wrapperR).setVisibility(View.VISIBLE);
        findViewById(R.id.recycleview).setVisibility(View.GONE);

        WrapperRecycleView wrapperRecycleView = findViewById(R.id.wrapperR);
        wrapperRecycleView.setLayoutManager(new LinearLayoutManager(this));
        wrapperRecycleView.setAdapter(adapter);
        wrapperRecycleView.addHeadView(LayoutInflater.from(this).inflate(R.layout.layout_header_view, wrapperRecycleView, false));
        wrapperRecycleView.addFootView(new Button(this));
        //这时再去考虑一个事情，我们通过装饰者模式把adapter封装了一层，如果adpater有数据更新，导致变动，这时会有问题吗？
        //这时会发现，并未更新，原因是装饰类，并未做事件响应
        adapter.notifyDataSetChanged();
    }

    static class MyRecycleViewAdapter extends RecyclerView.Adapter {
        private static final String TAG = MyRecycleViewAdapter.class.getSimpleName();
        private Context mContext;
        private List<String> data;

        MyRecycleViewAdapter(Context context) {
            mContext = context;
        }


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recycleview_item, null));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            Log.d(TAG, "onBindViewHolder" + data.get(position));
            TextView textView = holder.itemView.findViewById(R.id.textview);
            textView.setText(data.get(position));
            int pos = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //这时，只是更新了当前的adpter
                    data.remove(pos);
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }

        public void setData(List<String> ints) {
            data = ints;
        }

        static class MyViewHolder extends RecyclerView.ViewHolder {
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}