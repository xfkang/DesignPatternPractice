package com.itbird.design.chaiin.demo;

/**
 * Created by itbird on 2022/3/1
 */
public abstract class Handler {
    Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    String TAG() {
        return getClass().getSimpleName();
    }

    abstract void handlerRequeset(Request requestH);
}
