package com.itbird.design.strategy;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import timber.log.Timber;

/**
 * Created by itbird on 2022/6/21
 */
public class CrashReportingTree extends Timber.Tree {
    @Override
    protected void log(int priority, String tag, @NonNull String message, Throwable t) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            //过滤debug日志和所有日志
            return;
        }
        //TODO 上传平台 or 保存本地
    }
}
