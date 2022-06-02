package com.itbird.design.factory.demo.v2;

import android.util.Log;

import com.itbird.design.factory.demo.v1.IProduct;

/**
 * 具体的产品d
 * Created by itbird on 2022/6/1
 */
public class ConCreteIProductD implements IProduct {
    private static final String TAG = ConCreteIProductD.class.getSimpleName();

    @Override
    public void function() {
        Log.d(TAG, "ConCreteIProductD function");
    }
}
