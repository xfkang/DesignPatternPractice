package com.itbird.design.adapter.listview.v3;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * 自定义listview V3
 * 此时的view已经有了适配器、数据自动监听，但是还是缺少一些东西
 * 滑动时，会卡顿，不流畅，而且数据量大时，并没有进行view复用，会存在内存泄露的风险
 * Created by itbird on 2022/6/29
 */
public class ItbirdListViewV3 extends ScrollView {
    LinearLayout linearLayout;
    Context mContext;
    BaseAdapterV3 mAdapter;

    public ItbirdListViewV3(Context context) {
        super(context);
        init(context);
    }

    public ItbirdListViewV3(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ItbirdListViewV3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ItbirdListViewV3(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void setAdapter(BaseAdapterV3 adapter) {
        if (adapter == null) {
            return;
        }

        this.mAdapter = adapter;
        mAdapter.unregisterAll();
        AdapteDataSetObserver adapteDataSetObserver = new AdapteDataSetObserver();
        mAdapter.registerObserver(adapteDataSetObserver);
        //根据适配器数据，进行view的添加
        for (int i = 0; i < mAdapter.getCount(); i++) {
            addView(mAdapter.getItemView(i));
        }
    }

    public void init(Context context) {
        mContext = context;
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        super.addView(linearLayout);
    }

    public void addView(View view) {
        if (linearLayout == null) {
            init(mContext);
        }
        linearLayout.addView(view);
    }

    class AdapteDataSetObserver extends DataSetObserver {

        @Override
        public void onChanged() {
            linearLayout.removeAllViews();
            //根据适配器数据，进行view的添加
            for (int i = 0; i < mAdapter.getCount(); i++) {
                addView(mAdapter.getItemView(i));
            }
        }
    }
}
