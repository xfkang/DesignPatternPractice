package com.itbird.design.singleton.activitymanager;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.itbird.design.R;

/**
 * 测试单例模式
 * Created by itbird on 2022/6/9
 */
public class SingletonActivity extends BaseActivity {
    Button button;
    TextView textView;

    @Override
    int getLayout() {
        return R.layout.activity_common;
    }

    @Override
    void init() {
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