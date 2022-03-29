package com.itbird.design.principle.mvp.v1;


import java.lang.ref.WeakReference;

/**
 * Created by itbird on 2022/3/29
 */
public class BasePresenter<V extends IView> implements IPresenter {
    WeakReference<V> mIView;

    @Override
    public void onAttach(IView iView) {
        mIView = new WeakReference<>((V) iView);
    }

    @Override
    public void onDetach() {
        mIView = null;
    }

    @Override
    public V getView() {
        if (mIView != null) {
            mIView.get();
        }
        return null;
    }

    @Override
    public boolean isViewAttached() {
        return mIView != null && mIView.get() != null;
    }
}
