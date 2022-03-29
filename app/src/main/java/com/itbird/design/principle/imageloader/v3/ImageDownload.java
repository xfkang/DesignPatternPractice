package com.itbird.design.principle.imageloader.v3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 下载功能类
 * Created by itbird on 2022/3/28
 */
public class ImageDownload {
    private ExecutorService mExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private Handler mHandler;
    private final String TAG = ImageDownload.class.getSimpleName();
    private DownloadCallback mDownloadCallback;

    public ImageDownload(Context context, DownloadCallback callback) {
        mHandler = new Handler(context.getMainLooper());
        mDownloadCallback = callback;
    }

    public interface DownloadCallback {
        void downloadSuccess(String url, Bitmap bitmap);
    }

    /**
     * 下载图片
     *
     * @param url
     * @param imageView
     */
    public void downloadImage(String url, ImageView imageView) {
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
                if (mDownloadCallback != null) {
                    mDownloadCallback.downloadSuccess(url, bitmap);
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
