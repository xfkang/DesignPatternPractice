package com.itbird.design.principle.imageloader.v1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 自定义图片加载框架 V1.0版本
 * Created by itbird on 2022/3/28
 */
public class ImageLoader {
    private static final String TAG = ImageLoader.class.getSimpleName();
    //这里使用LruCanche算法，做内存存储，原理其实就是基于hashmap，存储key、value
    private LruCache<String, Bitmap> mImageCache = null;
    //下载线程池
    private ExecutorService mExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private Handler mHandler;
    private static volatile ImageLoader mInstance = null;

    private ImageLoader(Context context) {
        mHandler = new Handler(context.getMainLooper());
        initLruCache();
    }

    /**
     * 初始化LruCanche算法
     * 需要注意两点，一个是最大内存大小，一个sizeOf每个value的大小
     * 必须指定这两个，因为会涉及到LruCache的存儲于回收
     */
    private void initLruCache() {
        //获取虚拟机最大内存
        int memorySize = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //以最大内存的四分之一去作为算法缓存
        mImageCache = new LruCache<String, Bitmap>(memorySize / 4) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //每张图片占用的内存大小
                return value.getAllocationByteCount() / 1024;
            }
        };
    }

    public static ImageLoader getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 给view设置图片
     *
     * @param url
     * @param imageView
     */
    public void setImageView(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url) || imageView == null) {
            return;
        }

        Bitmap bitmap = mImageCache.get(url);
        Log.e(TAG, "" + bitmap);
        if (bitmap == null) {
            downloadImage(url, imageView);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    /**
     * 下载图片
     *
     * @param url
     * @param imageView
     */
    private void downloadImage(String url, ImageView imageView) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadUrlBitmap(url);
                Log.e(TAG, "url = " + url);
                Log.e(TAG, "" + bitmap.getByteCount());

                //cackback主线程
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
                if (mImageCache != null) {
                    mImageCache.put(url, bitmap);
                }
            }
        });
    }

    /**
     * 真正的下图图片函数
     *
     * @param urlString
     * @return
     */
    private Bitmap downloadUrlBitmap(String urlString) {
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        Bitmap bitmap = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            bitmap = BitmapFactory.decodeStream(in);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] bytes = bos.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}
