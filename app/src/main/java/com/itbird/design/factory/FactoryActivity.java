package com.itbird.design.factory;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.design.R;
import com.itbird.design.factory.demo.v2.ConCreteIProductD;
import com.itbird.design.factory.demo.v2.ConcreteFactory;
import com.itbird.design.factory.imageLoader.v5.GlideImageLoader;
import com.itbird.design.factory.imageLoader.v5.ImageLoaderFactory;
import com.itbird.design.factory.imageLoader.v5.PicassoImageLoader;
import com.itbird.design.factory.storage.MemoryStorageHandler;
import com.itbird.design.factory.storage.PreferencesStorageHandler;
import com.itbird.design.factory.storage.StorageFactroy;


/**
 * 测试工厂模式
 * 简单工厂、抽象工厂模式，实现方式演变，imageloader扩展，存储框架扩展，bitmapfactory，shareprefence源码分析
 * Created by itbird on 2022/6/9
 */
public class FactoryActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        imageView = findViewById(R.id.imageview);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testFactoryPatterm();
            }
        });
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
}



