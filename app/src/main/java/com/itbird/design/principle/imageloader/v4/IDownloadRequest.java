package com.itbird.design.principle.imageloader.v4;

import android.graphics.Bitmap;

/**
 * downlaod 请求基类
 * Created by itbird on 2022/3/30
 */
public interface IDownloadRequest {
    void download(String url, DownloadCallback<Bitmap> callback);
}
