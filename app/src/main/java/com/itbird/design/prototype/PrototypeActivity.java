package com.itbird.design.prototype;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.design.R;
import com.itbird.design.prototype.demo.ConcreateProotype;
import com.itbird.design.prototype.demo.Prototype;
import com.itbird.design.prototype.word.v1.WordV1DataInfo;
import com.itbird.design.prototype.word.v2.WordV2DataInfo;
import com.itbird.design.prototype.word.v3.WordV3DataInfo;

import java.util.ArrayList;

import okhttp3.OkHttpClient;


/**
 * 测试原型模式
 * Created by itbird on 2022/7/4
 */
public class PrototypeActivity extends AppCompatActivity {

    private static final String TAG = PrototypeActivity.class.getSimpleName();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        textView = findViewById(R.id.textview);

        /**
         * 源码和结构
         */
        tesPrototypePatterm();
//        testV1WordData();
//        testV2WordData();
        testV3WordData();
    }


    /**
     * v3 data copy
     */
    private void testV3WordData() {
        Log.d(TAG, "打印原始文档");
        WordV3DataInfo dataInfo = new WordV3DataInfo();
        dataInfo.setTitle("文档1");
        dataInfo.addContent("1111111111");
        dataInfo.addContent("2222222222");
        dataInfo.addContent("33333333333");
        Log.d(TAG, dataInfo.toString());

        Log.d(TAG, "打印修改后的文档");
        WordV3DataInfo dataInfo2 = dataInfo.clone();
        dataInfo2.setTitle("我是复制之后的文档");
        dataInfo2.addContent("44444444444");
        Log.d(TAG, dataInfo2.toString());


        Log.d(TAG, "打印原始文档");
        Log.d(TAG, dataInfo.toString());
    }

    /**
     * v2 data copy
     */
    private void testV2WordData() {
        Log.d(TAG, "打印原始文档");
        WordV2DataInfo dataInfo = new WordV2DataInfo();
        dataInfo.setTitle("文档1");
        dataInfo.addContent("1111111111");
        dataInfo.addContent("2222222222");
        dataInfo.addContent("33333333333");
        Log.d(TAG, dataInfo.toString());

        Log.d(TAG, "打印修改后的文档");
        WordV2DataInfo dataInfo2 = dataInfo.clone();
        dataInfo2.setTitle("我是复制之后的文档");
        dataInfo2.addContent("44444444444");
        Log.d(TAG, dataInfo2.toString());


        Log.d(TAG, "打印原始文档");
        Log.d(TAG, dataInfo.toString());
    }

    /**
     * v1 data copy
     */
    private void testV1WordData() {
        WordV1DataInfo dataInfo = new WordV1DataInfo();
        dataInfo.setTitle("文档1");
        dataInfo.addContent("1111111111");
        dataInfo.addContent("2222222222");
        dataInfo.addContent("33333333333");
        textView.setText(dataInfo.toString());

        WordV1DataInfo dataInfo2 = dataInfo.copy();
        dataInfo2.setTitle("我是复制之后的文档");
        textView.setText(dataInfo2.toString());

//        System.arraycopy();
    }


    /**
     * 测试原型模式
     */
    private void tesPrototypePatterm() {
        Prototype concreateProotype = new ConcreateProotype();
        Log.d(TAG, String.valueOf(concreateProotype));

        ConcreateProotype concreateProotype1 = (ConcreateProotype) concreateProotype.clone();
        Log.d(TAG, String.valueOf(concreateProotype1));

        /**
         * intent源码
         */
        Intent intent = (Intent) getIntent().clone();
        /**
         * arraylist源码
         */
        ArrayList<String> list = new ArrayList<>();
        list.clone();
        /**
         * okhttp
         */
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        OkHttpClient newokHttpClient = okHttpClient.newBuilder().build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

