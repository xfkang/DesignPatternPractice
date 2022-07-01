package com.itbird.design.adapter.listview.v1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * 自定义listview V1
 * Created by itbird on 2022/6/29
 */
public class ItbirdListViewV1 extends ScrollView {
    LinearLayout linearLayout;
    Context mContext;

    public ItbirdListViewV1(Context context) {
        super(context);
        init(context);
    }

    public ItbirdListViewV1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ItbirdListViewV1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ItbirdListViewV1(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        mContext = context;
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        addView(linearLayout, 0);
    }

    public void addView(View view) {
        if (linearLayout == null) {
            init(mContext);
        }
        linearLayout.addView(view);
    }
}
