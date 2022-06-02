package com.itbird.design.principle.imageloader.v4;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.itbird.design.factory.imageLoader.v5.ILoadImage;

import java.lang.ref.WeakReference;

/**
 * 自定义图片加载框架
 * imageloader优化，分离图片下载模块，实现下载模块可以自定义更换
 * Created by itbird on 2022/3/28.
 */
public class ImageLoader implements ILoadImage {
    private static final String TAG = ImageLoader.class.getSimpleName();
    private static volatile ImageLoader mInstance = null;
    /**
     * 缓存框架
     */
    private ICache mImageCache;
    private ICache<Bitmap> mDefaultCache = new MemoryCache();
    /**
     * download框架
     */
    private IDownloadRequest mDownloadRequest;
    private IDownloadRequest mDefaultDownloadRequest = new MyImageDownload();
    /**
     * 弱引用，防止内存泄露
     */
    private WeakReference<Context> mRef;
    private Handler mHandler;

    private ImageLoader(Context context) {
        mRef = new WeakReference<Context>(context);
        mHandler = new Handler(mRef.get().getMainLooper());
    }

    public void setImageDownloadImpl(IDownloadRequest downloadRequest) {
        mDownloadRequest = downloadRequest;
    }

    public void setImageCache(ICache cache) {
        mImageCache = cache;
        mImageCache.init();
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
     * @param context
     * @param imageView
     * @param url
     */
    @Override
    public void setImageView(Context context, ImageView imageView, String url) {
        if (TextUtils.isEmpty(url) || imageView == null) {
            return;
        }

        if (mImageCache == null) {
            mImageCache = mDefaultCache;
            mImageCache.init();
        }

        if (mDownloadRequest == null) {
            mDownloadRequest = mDefaultDownloadRequest;
        }

        Bitmap bitmap = (Bitmap) mImageCache.getCache(url);
        Log.e(TAG, "" + bitmap);
        if (bitmap == null) {
            mDownloadRequest.download(url, new DownloadCallback<Bitmap>() {
                @Override
                public void downloadSuccess(String url, Bitmap result) {
                    //cackback主线程
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(result);
                        }
                    });
                    if (mImageCache != null) {
                        mImageCache.putCache(url, result);
                    }
                }

                @Override
                public void downloadProcess(int proecss) {

                }

                @Override
                public void downloadError(int errorCode, String errorMsg) {

                }
            });
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }
}
