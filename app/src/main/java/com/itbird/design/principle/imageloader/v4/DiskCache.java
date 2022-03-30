package com.itbird.design.principle.imageloader.v4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.itbird.design.APP;
import com.itbird.design.utils.CloseUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 硬盘缓存实现
 * Created by itbird on 2022/2/28
 */
public class DiskCache implements ICache<Bitmap> {
    private String TAG = DiskCache.class.getSimpleName();
    private File mRootDir = null;

    @Override
    public void init() {
        mRootDir = APP.getInstance().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    }

    @Override
    public Bitmap getCache(String url) {
        return BitmapFactory.decodeFile(mRootDir.getAbsolutePath() + url.substring(url.lastIndexOf("/")) + ".png");
    }

    @Override
    public void putCache(String url, Bitmap bitmap) {
        File file = new File(mRootDir.getAbsolutePath() + url.substring(url.lastIndexOf("/")) + ".png");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, file.getAbsolutePath());
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            e.printStackTrace();
        } finally {
            CloseUtils.close(outputStream);
        }
    }

    @Override
    public void removeCache(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }

        File file = new File("/sdcard/Pictures/" + url);
        if (file == null || !file.exists()) {
            return;
        }
        file.delete();
    }
}
