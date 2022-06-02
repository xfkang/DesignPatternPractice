package com.itbird.design.factory.imageLoader.v5;

/**
 * Created by itbird on 2022/6/1
 */
public interface IImageFactroy {
    <T extends ILoadImage> T getImageLoader(Class<T> tClass);
}
