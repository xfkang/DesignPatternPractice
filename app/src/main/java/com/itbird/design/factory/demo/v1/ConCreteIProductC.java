package com.itbird.design.factory.demo.v1;

import android.util.Log;

/**
 * 具体的产品c
 * Created by itbird on 2022/6/1
 */
public class ConCreteIProductC implements IProduct {
    private static final String TAG = ConCreteIProductC.class.getSimpleName();

    @Override
    public void function() {
        Log.d(TAG, "ConCreteProductC function");
    }
}
