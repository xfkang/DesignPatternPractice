package com.itbird.design.facade;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.itbird.design.R;
import com.itbird.design.principle.imageloader.v3.ImageLoader;


/**
 * 测试外观模式
 * Created by itbird on 2022/7/11
 */
public class FacadeActivity extends AppCompatActivity {

    private static final String TAG = FacadeActivity.class.getSimpleName();
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facade);
        viewContextSource();
        viewGlideSource();
    }

    /**
     * 测试glide内存泄露的问题
     */
    private void testGlideMemoryOOM() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        TestFragment testFragment = TestFragment.newInstance("a", "b");
        transaction.add(R.id.framelayout, testFragment, "testFragment").commit();
    }

    private void viewGlideSource() {
        imageView = findViewById(R.id.imageview);
        Glide.with(this).load("https://img-blog.csdn.net/20160903083245762").into(imageView);
        ImageLoader.getInstance(this).setImageView("https://img-blog.csdn.net/20160903083245762", imageView);
    }

    /**
     * 为了方便查看context源码
     */
    private void viewContextSource() {
        Context context = getBaseContext();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void testShowFragment(View view) {
        testGlideMemoryOOM();
    }
}

