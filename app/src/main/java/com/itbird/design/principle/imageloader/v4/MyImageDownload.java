package com.itbird.design.principle.imageloader.v4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.itbird.design.utils.CloseUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 下载功能类
 * Created by itbird on 2022/3/28
 */
public class MyImageDownload implements IDownloadRequest {
    private final int CORE_THREAD_SIZE = Runtime.getRuntime().availableProcessors() + 1;
    private final int MAX_THREAD_SIZE = CORE_THREAD_SIZE;
    private ThreadPoolExecutor mExecutor = new ThreadPoolExecutor(CORE_THREAD_SIZE, MAX_THREAD_SIZE, 60, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>());
    private final String TAG = MyImageDownload.class.getSimpleName();

    public MyImageDownload() {
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
            CloseUtils.close(in);
        }
        return bitmap;
    }

    @Override
    public void download(String url, DownloadCallback<Bitmap> callback) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadUrlBitmap(url);
                Log.e(TAG, "url = " + url);
                Log.e(TAG, "" + bitmap.getByteCount());
                if (callback != null) {
                    callback.downloadSuccess(url, bitmap);
                }
            }
        });
    }
}
