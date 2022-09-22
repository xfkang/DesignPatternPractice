package com.itbird.design.observer;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.design.R;
import com.itbird.design.observer.demo.ObservableImpl;
import com.itbird.design.observer.demo.Observer;


/**
 * 测试观察者模式
 * 观察者模式，eventbus源码分析，asynctask源码分析，xjava源码分析
 * Created by itbird on 2022/6/9
 */
public class ObserverActivity extends AppCompatActivity implements Observer {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        textView = findViewById(R.id.textview);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testObserverPatterm();
            }
        });
    }


    /**
     * 测试观察者模式
     */
    private void testObserverPatterm() {
        ObservableImpl.getmInstance().attatchObservable(this);
        ObservableImpl.getmInstance().notifySetChanged("kkkkk");
    }

    @Override
    protected void onDestroy() {
        ObservableImpl.getmInstance().detachObservable(this);
        super.onDestroy();
    }

    @Override
    public void updateUI(String text) {
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
        }
    }
}

