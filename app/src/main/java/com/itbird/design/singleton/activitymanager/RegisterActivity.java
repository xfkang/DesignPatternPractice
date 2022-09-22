package com.itbird.design.singleton.activitymanager;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.itbird.design.R;


public class RegisterActivity extends BaseActivity {
    Button button;
    TextView textView;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        textView = findViewById(R.id.textview);
        button = findViewById(R.id.button);
        textView.setText(RegisterActivity.class.getSimpleName());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getInstance().finishActivity(LoginActivity.class);
            }
        });
    }
}