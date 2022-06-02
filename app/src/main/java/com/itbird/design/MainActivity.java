package com.itbird.design;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.design.builder.dialog.CommonDialog;
import com.itbird.design.builder.navigationbar.v1.NavigationBar;
import com.itbird.design.chaiin.demo.Request;
import com.itbird.design.chaiin.demo.RequestHandler1;
import com.itbird.design.chaiin.demo.RequestHandler2;
import com.itbird.design.chaiin.demo.RequestHandler3;
import com.itbird.design.chaiin.upgrade.v1.UpgradeManager;
import com.itbird.design.factory.demo.v1.ConCreteIProductA;
import com.itbird.design.factory.demo.v2.ConCreteIProductD;
import com.itbird.design.factory.demo.v2.ConcreteFactory;
import com.itbird.design.factory.imageLoader.v5.GlideImageLoader;
import com.itbird.design.factory.imageLoader.v5.ImageLoaderFactory;
import com.itbird.design.factory.imageLoader.v5.PicassoImageLoader;
import com.itbird.design.factory.storage.MemoryStorageHandler;
import com.itbird.design.factory.storage.PreferencesStorageHandler;
import com.itbird.design.factory.storage.StorageFactroy;
import com.itbird.design.observer.demo.ObservableImpl;
import com.itbird.design.observer.demo.Observer;
import com.itbird.design.principle.imageloader.v3.DiskCache;
import com.itbird.design.principle.imageloader.v3.ImageLoader;
import com.itbird.design.singleton.activitymanager.ActivityManagerTestActivity;


public class MainActivity extends AppCompatActivity implements Observer, UIHandler.IHandler {
    Button button;
    TextView textView;
    ImageView imageView;
    UIHandler mUIHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAction();
        mUIHandler = new UIHandler(this);

        /**
         * 测试六大基本原则
         */
        testPrinciple();
        /**
         * 测试责任链模式
         */
        testChainPatterm();
        /**
         * 测试观察者模式
         */
        testObserverPatterm();
        /**
         * 测试单例模式，activitymanager实现
         */
        testSingletomPatterm();
        /**
         * 测试建造者模式
         */
        testBuilderPatterm();
        /**
         * 测试工厂模式
         */
        testFactoryPatterm();
    }

    /**
     * 测试工厂模式
     */
    private void testFactoryPatterm() {
        ConCreteIProductD productD = ConcreteFactory.getInstance().createProduct(ConCreteIProductD.class);
        productD.function();

        StorageFactroy.getInstance().getStorageHandler(MemoryStorageHandler.class).getString("123");
        StorageFactroy.getInstance().getStorageHandler(PreferencesStorageHandler.class).save("123", "3232");

        /**
         * 分别使用不同的图片加载框架
         */
        ImageLoaderFactory.getInstance().getImageLoader(PicassoImageLoader.class).setImageView(this, imageView, "https://img-blog.csdn.net/20160903083245762");
        ImageLoaderFactory.getInstance().getImageLoader(com.itbird.design.principle.imageloader.v4.ImageLoader.class).setImageView(this, imageView, "https://img-blog.csdn.net/20160903083245762");
        ImageLoaderFactory.getInstance().getImageLoader(GlideImageLoader.class).setImageView(this, imageView, "https://img-blog.csdn.net/20160903083245762");
    }

    private void testBuilderPatterm() {
        new NavigationBar.Builder(MainActivity.this, R.layout.navigation_layout, (ViewGroup) getWindow().getDecorView())
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
        CommonDialog dialog = new CommonDialog.Builder(MainActivity.this)
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

    private void testSingletomPatterm() {
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityManagerTestActivity.class));
            }
        });
    }


    private void testPrinciple() {
        for (int i = 0; i < url.length; i++) {
            Message message = Message.obtain();
            message.what = MSG_SET_IMAGE_VIEW;
            message.obj = url[i];
            mUIHandler.sendMessageDelayed(message, 1000);
        }
    }

    private void initView() {
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textview);
        imageView = findViewById(R.id.imageview);
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

    /**
     * 图片地址集合
     */
    private final String url[] = {
            "https://img-blog.csdn.net/20160903083245762",
            "https://img-blog.csdn.net/20160903083252184",
            "https://img-blog.csdn.net/20160903083257871",
            "https://img-blog.csdn.net/20160903083311972",
            "https://img-blog.csdn.net/20160903083319668",
            "https://img-blog.csdn.net/20160903083326871"
    };

    private final int MSG_SET_IMAGE_VIEW = 1;

    @Override
    public void handleMessage(Message message) {
        if (message == null) {
            return;
        }

        switch (message.what) {
            case MSG_SET_IMAGE_VIEW:
                //V2版本
                //ImageLoader.getInstance(this).setImageView((String) message.obj, imageView);
                //V3版本
                ImageLoader.getInstance(this).setImageCache(new DiskCache());
                ImageLoader.getInstance(this).setImageView((String) message.obj, imageView);
                break;
        }
    }
}