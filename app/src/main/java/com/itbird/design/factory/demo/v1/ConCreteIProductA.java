package com.itbird.design.factory.demo.v1;

import android.util.Log;

/**
 * 具体的产品A
 * Created by itbird on 2022/6/1
 */
public class ConCreteIProductA implements IProduct {
    private static final String TAG = ConCreteIProductA.class.getSimpleName();

    @Override
    public void function() {
        Log.d(TAG, "ConCreteProductA function");
    }
}
