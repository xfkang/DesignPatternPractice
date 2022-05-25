package com.itbird.design.singleton.activitymanager;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.itbird.design.R;


public class LoginActivity extends BaseActivity {
    Button button;
    TextView textView;

    @Override
    int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    void init() {
        textView = findViewById(R.id.textview);
        button = findViewById(R.id.button);
        textView.setText(RegisterActivity.class.getSimpleName());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}