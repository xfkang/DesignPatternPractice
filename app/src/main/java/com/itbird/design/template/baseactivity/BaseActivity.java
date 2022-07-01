package com.itbird.design.template.baseactivity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * BaseActivity
 * Created by itbird on 2022/6/17
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. 设置布局
        setContentView(getLayoutId());
        // 2. 初始化Title
        initTitle();
        // 3. 访问接口数据（initData）
        if (isInitData()) {
            initData(savedInstanceState);
        }
        // 4. 初始化View
        initView();
    }

    /**
     * 钩子函数，用来控制模板里面的执行步骤
     * @return
     */
    public boolean isInitData() {
        return true;
    }

    protected abstract int getLayoutId();

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract void initTitle();

    protected abstract void setContentView();
}

