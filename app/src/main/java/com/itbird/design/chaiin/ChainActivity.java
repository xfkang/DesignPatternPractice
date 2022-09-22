package com.itbird.design.chaiin;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.design.R;
import com.itbird.design.chaiin.demo.Request;
import com.itbird.design.chaiin.demo.RequestHandler1;
import com.itbird.design.chaiin.demo.RequestHandler2;
import com.itbird.design.chaiin.demo.RequestHandler3;
import com.itbird.design.chaiin.upgrade.v1.UpgradeManager;


/**
 * 测试责任链模式
 * 责任链模式，view事件分发机制，多系统升级框架设计，okhttp源码分析
 * Created by itbird on 2022/6/9
 */
public class ChainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testChainPatterm();
            }
        });
    }


    /**
     * 测试责任链模式
     */
    private void testChainPatterm() {
        RequestHandler1 handler1 = new RequestHandler1();
        RequestHandler2 handler2 = new RequestHandler2();
        RequestHandler3 handler3 = new RequestHandler3();
        handler1.setNext(handler2);
        handler2.setNext(handler3);
        Request request = new Request();
        request.setUrl("xxxxxxxxxxxx");
        handler1.handlerRequeset(request);
        UpgradeManager.getInstance().startUpgrade("/sdcard/xxx.zip");
    }
}
