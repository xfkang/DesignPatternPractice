package com.itbird.design.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itbird.design.R;
import com.itbird.design.adapter.demo.Adaptee;
import com.itbird.design.adapter.demo.ClassAdapter;
import com.itbird.design.adapter.demo.ObjectAdapter;
import com.itbird.design.adapter.listview.v1.ItbirdListViewV1;
import com.itbird.design.adapter.listview.v2.ItbirdListViewV2;
import com.itbird.design.adapter.listview.v2.ListAdapterV2;
import com.itbird.design.adapter.listview.v3.ItbirdListViewV3;
import com.itbird.design.adapter.listview.v3.ListAdapterV3;
import com.itbird.design.template.baseactivity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 适配器模式
 * Created by itbird on 2022/6/20
 */
public class AdapterActivity extends BaseActivity {

    private static final String TAG = AdapterActivity.class.getSimpleName();
    List<String> datalist = new ArrayList<>();
    ItbirdListViewV3 listViewV3;
    ItbirdListViewV2 listViewV2;
    ItbirdListViewV1 listViewV1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_adapter;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //准备数据
        for (int i = 0; i < 100; i++) {
            datalist.add("i am " + i);
        }
    }

    /**
     * 钩子函数，子类实现，用来控制父类中，某个方法是否执行
     *
     * @return
     */
    @Override
    public boolean isInitData() {
        return true;
    }

    @Override
    protected void initView() {
        listViewV1 = findViewById(R.id.listviewv1);
        listViewV2 = findViewById(R.id.listviewv2);
        listViewV3 = findViewById(R.id.listviewv3);

        testDemo();

        viewListViewSource();
    }

    /**
     * 为了查看listView源码
     */
    private void viewListViewSource() {
        ListView listView = new ListView(this);
        MyListViewAdapter myListViewAdapter = new MyListViewAdapter();
        listView.setAdapter(myListViewAdapter);
    }

    /**
     * 测试类适配器和对象适配器
     */
    private void testDemo() {
        //可以看到此处类适配器的坏处，就是持有了拥有了父类所有的方法，对外公开
        ClassAdapter adapter = new ClassAdapter();
        adapter.output5V();

        //一般使用的都是对象适配器
        ObjectAdapter objectAdapter = new ObjectAdapter(new Adaptee());
        objectAdapter.output5V();
    }

    /**
     * 添加观察者的listview
     */
    public void testListViewV3(View view) {
        datalist.clear();
        for (int i = 0; i < 100; i++) {
            datalist.add("i am testListviewV3 " + i);
        }
        listViewV1.setVisibility(View.GONE);
        listViewV2.setVisibility(View.GONE);
        listViewV3.setVisibility(View.VISIBLE);
        //创建adapter
        ListAdapterV3 adapter = new ListAdapterV3(this);
        adapter.setData(datalist);
        //将adapter给到listview使用，此时listview相当于客户端
        listViewV3.setAdapter(adapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                datalist.clear();
                for (int i = 0; i < 100; i++) {
                    datalist.add("refrsh testListviewV3 " + i);
                }
                adapter.notifySetChanged();
            }
        }, 5000);

        // TODO 此时的view已经有了适配器、数据自动监听，但是还是缺少一些东西
        // TODO 滑动时，会卡顿，不流畅，而且数据量大时，并没有进行view复用，会存在内存泄露的风险
    }

    /**
     * 自定义listview，使用adapter
     */
    public void testListViewV2(View view) {
        datalist.clear();
        for (int i = 0; i < 100; i++) {
            datalist.add("i am testListviewV2 " + i);
        }
        listViewV1.setVisibility(View.GONE);
        listViewV3.setVisibility(View.GONE);
        listViewV2.setVisibility(View.VISIBLE);

        //创建adapter
        ListAdapterV2 adapter = new ListAdapterV2(this);
        adapter.setData(datalist);
        //将adapter给到listview使用，此时listview相当于客户端
        listViewV2.setAdapter(adapter);

        // TODO 大家此时发现了没有，我们自定义的listview好像缺少点什么？
        // TODO 缺少了，数据变化时，可以主动去更新界面的操作，是不？
        // TODO  还记得之前讲过的观察者模式吗？这里就可以使用了
        // TODO 首先清楚，观察者模式是有观察者和被观察者角色区分，这里数据是被观察者，listview是观察者
    }


    /**
     * 简单的listview
     */
    public void testListViewV1(View view) {
        listViewV1.setVisibility(View.VISIBLE);
        listViewV2.setVisibility(View.GONE);
        listViewV3.setVisibility(View.GONE);
        /**
         * 其实listview需要的是view，所以我们使用适配器模式，在中间增加一层，将数据转换为view即可
         */
        for (String a : datalist) {
            TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.item_listview, null);
            textView.setText(a);
            listViewV1.addView(textView);
        }
        // TODO 最简单的listview实现，数据和item的创建，都放在外界
    }


    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {

    }

    /**
     * 自定义adapter
     */
    class MyListViewAdapter extends BaseAdapter {
        /**
         * 开发者可以传，任意类型的数据进来
         */
        List<String> data = new ArrayList<>();
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}