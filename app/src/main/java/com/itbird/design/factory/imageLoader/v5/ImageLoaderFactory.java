package com.itbird.design.factory.imageLoader.v5;

/**
 * Created by itbird on 2022/6/1
 */
public class ImageLoaderFactory implements IImageFactroy {
    private ImageLoaderFactory() {
    }

    public static ImageLoaderFactory getInstance() {
        return ImageLoaderFactory.ImageLoaderFactoryHolder.instance;
    }

    private final static class ImageLoaderFactoryHolder {
        final static ImageLoaderFactory instance = new ImageLoaderFactory();
    }

    @Override
    public <T extends ILoadImage> T getImageLoader(Class<T> tClass) {
        Class cts = null;
        try {
            cts = Class.forName(tClass.getCanonicalName());
            return (T) cts.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
