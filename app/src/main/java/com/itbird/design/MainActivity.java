package com.itbird.design;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.itbird.design.chaiin.demo.Request;
import com.itbird.design.chaiin.demo.RequestHandler1;
import com.itbird.design.chaiin.demo.RequestHandler2;
import com.itbird.design.chaiin.demo.RequestHandler3;
import com.itbird.design.chaiin.upgrade.UpgradeManager;
import com.itbird.design.observer.demo.ObservableImpl;
import com.itbird.design.observer.demo.Observer;


public class MainActivity extends AppCompatActivity implements Observer {
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAction();

        testChainPatterm();
        testObserverPatterm();
    }

    private void initView() {
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textview);
    }

    private void initAction() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObservableImpl.getmInstance().notifySetChanged("kkkkk");
            }
        });
    }

    /**
     * 测试观察者模式
     */
    private void testObserverPatterm() {
        ObservableImpl.getmInstance().attatchObservable(this);
    }

    @Override
    protected void onDestroy() {
        ObservableImpl.getmInstance().detachObservable(this);
        super.onDestroy();
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

    @Override
    public void updateUI(String text) {
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
        }
    }
}