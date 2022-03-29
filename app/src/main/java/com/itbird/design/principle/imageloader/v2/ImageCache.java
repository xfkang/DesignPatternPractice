package com.itbird.design.principle.imageloader.v2;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 缓存功能类
 * Created by itbird on 2022/3/28
 */
public class ImageCache {
    //这里使用LruCanche算法，做内存存储，原理其实就是基于hashmap，存储key、value
    private LruCache<String, Bitmap> mImageCache = null;

    /**
     * 初始化LruCanche算法
     * 需要注意两点，一个是最大内存大小，一个sizeOf每个value的大小
     * 必须指定这两个，因为会涉及到LruCache的存儲于回收
     */
    public void initLruCache() {
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

    public Bitmap getCache(String url) {
        if (mImageCache != null) {
            mImageCache.get(url);
        }
        return null;
    }

    public void putCache(String url, Bitmap bitmap) {
        if (mImageCache != null) {
            mImageCache.put(url, bitmap);
        }
    }
}
