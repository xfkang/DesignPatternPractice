package com.itbird.design.builder.navigationbar.v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * 自定义NavigationBar
 * Created by itbird on 2022/5/26
 */
public class NavigationBar {

    protected NavigationBar() {
    }

    public static class Builder {
        /**
         * 视图布局id
         */
        int layoutID;
        View mCurrentView;
        ViewGroup parentView;

        /**
         * 初始化view
         *
         * @param context
         * @param layoutID
         * @param rootView
         */
        public Builder(Context context, int layoutID, ViewGroup rootView) {
            this.layoutID = layoutID;
            this.parentView = rootView;
            mCurrentView = LayoutInflater.from(context).inflate(layoutID, rootView, false);
        }

        public Builder setBackColor(int color) {
            mCurrentView.setBackgroundColor(color);
            return this;
        }


        /**
         * 设置textview文本
         *
         * @param viewId
         * @param text
         * @return
         */
        public Builder setTextToTextView(int viewId, String text) {
            TextView textView = findViewByID(viewId);
            textView.setText(text);
            return this;
        }

        /**
         * 设置button文本
         *
         * @param viewId
         * @param text
         * @return
         */
        public Builder setTextToButtonView(int viewId, String text) {
            Button button = findViewByID(viewId);
            button.setText(text);
            return this;
        }

        /**
         * 设置button事件
         *
         * @param viewId
         * @param onClickListener
         * @return
         */
        public Builder setOnClickListenerToButtonView(int viewId, View.OnClickListener onClickListener) {
            Button button = findViewByID(viewId);
            button.setOnClickListener(onClickListener);
            return this;
        }

        /**
         * 展示view
         *
         * @return
         */
        public ViewGroup show() {
            parentView.addView(mCurrentView, 0);
            return parentView;
        }

        public <T extends View> T findViewByID(int viewID) {
            return mCurrentView.findViewById(viewID);
        }
    }

}
