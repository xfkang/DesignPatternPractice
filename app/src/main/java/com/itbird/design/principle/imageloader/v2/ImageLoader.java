package com.itbird.design.principle.imageloader.v2;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

/**
 * 自定义图片加载框架
 * Created by itbird on 2022/3/28
 */
public class ImageLoader implements ImageDownload.DownloadCallback {
    private static final String TAG = ImageLoader.class.getSimpleName();
    private static volatile ImageLoader mInstance = null;
    private ImageCache mImageCache;
    private ImageDownload mImageDownload;

    private ImageLoader(Context context) {
        mImageCache = new ImageCache();
        mImageDownload = new ImageDownload(context, this);
        mImageCache.initLruCache();
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

        Bitmap bitmap = mImageCache.getCache(url);
        Log.e(TAG, "" + bitmap);
        if (bitmap == null) {
            mImageDownload.downloadImage(url, imageView);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void downloadSuccess(String url, Bitmap bitmap) {
        if (mImageCache != null) {
            mImageCache.putCache(url, bitmap);
        }
    }
}
