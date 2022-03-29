package com.itbird.design;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * 弱引用handlerr实封装，防止内存泄漏
 * Created by itbird on 2022/3/28
 */
public class UIHandler<T extends UIHandler.IHandler> extends Handler {
    WeakReference<T> mCallback;

    public UIHandler(T callback) {
        super(Looper.getMainLooper());
        mCallback = new WeakReference<T>(callback);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if (mCallback != null && mCallback.get() != null) {
            mCallback.get().handleMessage(msg);
        }
    }

    public interface IHandler {
        void handleMessage(Message message);
    }
}
