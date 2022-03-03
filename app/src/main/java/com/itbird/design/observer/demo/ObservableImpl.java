package com.itbird.design.observer.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itbird on 2022/3/3
 */
public class ObservableImpl implements Observable<Observer> {
    List<Observer> observers;
    private static volatile ObservableImpl mInstance;

    private ObservableImpl() {
        observers = new ArrayList<>();
    }

    public static ObservableImpl getmInstance() {
        if (mInstance == null) {
            synchronized (ObservableImpl.class) {
                if (mInstance == null) {
                    mInstance = new ObservableImpl();
                }
            }
        }
        return mInstance;
    }

    @Override
    public boolean attatchObservable(Observer observable) {
        synchronized (observers) {
            if (observable != null) {
                return observers.add(observable);
            }
            return false;
        }
    }

    @Override
    public boolean detachObservable(Observer observable) {
        synchronized (observers) {
            if (observable != null) {
                return observers.remove(observable);
            }
            return false;
        }
    }

    @Override
    public void notifySetChanged(String s) {
        synchronized (observers) {
            for (Observer observer : observers) {
                observer.updateUI(s);
            }
        }
    }
}
