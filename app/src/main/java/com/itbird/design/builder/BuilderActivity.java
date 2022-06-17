package com.itbird.design.builder;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.design.R;
import com.itbird.design.builder.dialog.CommonDialog;
import com.itbird.design.builder.navigationbar.v1.NavigationBar;

/**
 * 测试建造者模式
 * Created by itbird on 2022/6/9
 */
public class BuilderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testBuilderPatterm();
            }
        });
    }

    /**
     * 测试建造者模式
     */
    private void testBuilderPatterm() {
        new NavigationBar.Builder(this, R.layout.navigation_layout, (ViewGroup) getWindow().getDecorView())
                .setBackColor(com.google.android.material.R.color.design_default_color_on_secondary)
                .setTextToButtonView(R.id.back_button, "返回")
                .setTextToTextView(R.id.title_textview, "我是标题")
                .setOnClickListenerToButtonView(R.id.back_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog();
                    }
                }).show();
    }

    private void showDialog() {
        CommonDialog dialog = new CommonDialog.Builder(this)
                .setTitle("我是弹窗")
                .setMessage("快关闭我")
                .setPositiveButton("关闭弹窗", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

}
