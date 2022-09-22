package com.itbird.design.singleton.activitymanager;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * Created by itbird on 2022/5/24
 */
public abstract class BaseActivity extends Activity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ActivityManager.getInstance().addActivity(this);
        init();
    }

    public abstract int getLayout();

    public abstract void init();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().finishActivity(this);
    }
}
