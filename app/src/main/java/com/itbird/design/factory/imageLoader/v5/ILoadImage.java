package com.itbird.design.factory.imageLoader.v5;

import android.content.Context;
import android.widget.ImageView;

/**
 * 图片加载接口
 * Created by itbird on 2022/6/1
 */
public interface ILoadImage {
    void setImageView(Context context, ImageView imageView, String url);
}
