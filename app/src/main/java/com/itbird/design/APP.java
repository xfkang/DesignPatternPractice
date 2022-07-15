package com.itbird.design;

import android.app.Application;
import android.content.Context;


/**
 * Created by itbird on 2022/3/28
 */
public class APP extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getInstance() {
        return mContext;
    }
}
