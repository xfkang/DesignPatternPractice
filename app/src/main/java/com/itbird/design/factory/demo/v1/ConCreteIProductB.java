package com.itbird.design.factory.demo.v1;

import android.util.Log;

/**
 * 具体的产品B
 * Created by itbird on 2022/6/1
 */
public class ConCreteIProductB implements IProduct {
    private static final String TAG = ConCreteIProductB.class.getSimpleName();

    @Override
    public void function() {
        Log.d(TAG, "ConCreteProductB function");
    }
}
