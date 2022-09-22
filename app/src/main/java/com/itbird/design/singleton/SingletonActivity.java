package com.itbird.design.singleton;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.itbird.design.R;
import com.itbird.design.singleton.activitymanager.BaseActivity;
import com.itbird.design.singleton.activitymanager.LoginActivity;
import com.itbird.design.singleton.activitymanager.RegisterActivity;

/**
 * 测试单例模式
 * 单例模式，imageloader，activitymanager，系统getservice源码分析
 * Created by itbird on 2022/6/9
 */
public class SingletonActivity extends BaseActivity {
    Button button;
    TextView textView;

    @Override
    public int getLayout() {
        return R.layout.activity_common;
    }

    @Override
    public void init() {
        textView = findViewById(R.id.textview);
        button = findViewById(R.id.button);
        textView.setText(RegisterActivity.class.getSimpleName());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingletonActivity.this, LoginActivity.class));
            }
        });
    }
}