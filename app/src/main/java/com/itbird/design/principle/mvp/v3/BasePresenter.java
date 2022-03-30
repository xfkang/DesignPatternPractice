package com.itbird.design.principle.mvp.v3;

import java.lang.ref.WeakReference;

/**
 * Created by itbird on 2022/3/30
 */
public abstract class BasePresenter<V> implements IPresenter<V> {
    WeakReference<V> mIView;

    @Override
    public void onAttach(V view) {
        mIView = new WeakReference<V>(view);
    }

    @Override
    public void onDetach() {
        mIView = null;
    }

    @Override
    public boolean isViewAttached() {
        return mIView != null && mIView.get() != null;
    }

    @Override
    public V getView() {
        if (mIView != null) {
            return mIView.get();
        }
        return null;
    }
}
