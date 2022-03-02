package com.itbird.design.chaiin.demo;

import android.util.Log;

/**
 * Created by itbird on 2022/3/1
 */
public class RequestHandler1 extends Handler {
    @Override
    public void handlerRequeset(Request request) {
        Log.d(TAG(), "RequestHandler1 handlerRequeset" + request.getUrl());
        if (next != null) {
            next.handlerRequeset(request);
        }
    }
}
