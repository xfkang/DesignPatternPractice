package com.itbird.design.flyweight;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.itbird.design.R;
import com.itbird.design.flyweight.eventbus.EventMsg;
import com.itbird.design.flyweight.ticket.v1.TicketFactroyV1;
import com.itbird.design.flyweight.ticket.v2.TicketFactroyV2;



/**
 * 测试享元模式
 * 享元模式，定义，demo（买票，map，start，end），Message.obtain，eventbus，补充：eventbus源码的解析
 * Created by itbird on 2022/7/11
 */
public class FlyWeightActivity extends AppCompatActivity {

    private static final String TAG = FlyWeightActivity.class.getSimpleName();
    TextView textView;
    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        textView = findViewById(R.id.textview);
        tesFlyWeightPattermV1();
        tesFlyWeightPattermV2();
        testMessage();
        testJdkSource();
        testEventBus();
    }

    /**
     * 查看EventBus源码
     */
    private void testEventBus() {
//        EventBus.getDefault().register(this);
//        EventBus.getDefault().unregister(this);
//        EventBus.getDefault().post(new EventMsg("k"));
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    private void receiverEventBusMsg(EventMsg msg) {
//        //todo 处理消息
//    }

    /**
     * 查看jdk源码
     */
    private void testJdkSource() {
        String str1 = "abc";
        String str2 = new String("abc");
        String str3 = new String("abc");
        String str4 = "ab" + "c";

        Log.d(TAG, "" + str1.equals(str2));
        Log.d(TAG, "" + str1.equals(str4));
        Log.d(TAG, "" + str2.equals(str3));

        Log.d(TAG, "" + (str1 == str2));
        Log.d(TAG, "" + (str1 == str4));
        Log.d(TAG, "" + (str2 == str3));
    }

    /**
     * 查看message源码
     */
    private void testMessage() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
        /**
         * 初级的研发人员的写法
         */
        Message message = new Message();
        message.what = 1;
        message.obj = textView;
        mHandler.sendMessage(message);

        /**
         * 有经验一些的研发人员，可能会这样写
         */
        Message message1 = Message.obtain();
        message1.what = 1;
        message1.obj = textView;
        mHandler.sendMessage(message1);
    }


    /**
     * 测试享元模式
     */
    private void tesFlyWeightPattermV1() {
        TicketFactroyV1 factroy = new TicketFactroyV1();
        factroy.getTicket("大连", "北京").getPrice();
        factroy.getTicket("北京", "上海").getPrice();
        factroy.getTicket("北京", "关东").getPrice();
        factroy.getTicket("大连", "北京").getPrice();
    }

    /**
     * 测试享元模式
     */
    private void tesFlyWeightPattermV2() {
        TicketFactroyV2 factroy = new TicketFactroyV2();
        factroy.getTicket("大连", "北京").getPrice();
        factroy.getTicket("北京", "上海").getPrice();
        factroy.getTicket("北京", "关东").getPrice();
        factroy.getTicket("大连", "北京").getPrice();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

