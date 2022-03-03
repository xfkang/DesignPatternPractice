package com.itbird.design.observer.demo;

/**
 * Created by itbird on 2022/3/3
 */
public interface Observable<T> {
    boolean attatchObservable(T observable);

    boolean detachObservable(T observable);

    void notifySetChanged(String s);
}
