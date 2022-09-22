package com.itbird.design.principle;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.itbird.design.R;
import com.itbird.design.principle.imageloader.v3.DiskCache;
import com.itbird.design.principle.imageloader.v3.ImageLoader;
import com.itbird.design.utils.UIHandler;


/**
 * 面对六个基本原则，mvp演变，imageloader演变
 * Created by itbird on 2022/6/9
 */
public class PrincipleActivity extends AppCompatActivity implements UIHandler.IHandler {
    ImageView imageView;
    UIHandler mUIHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        mUIHandler = new UIHandler(this);
        imageView = findViewById(R.id.imageview);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testPrinciple();
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

