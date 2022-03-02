package com.itbird.design;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.itbird.design.chaiin.demo.Request;
import com.itbird.design.chaiin.demo.RequestHandler1;
import com.itbird.design.chaiin.demo.RequestHandler2;
import com.itbird.design.chaiin.demo.RequestHandler3;
import com.itbird.design.chaiin.upgrade.UpgradeManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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