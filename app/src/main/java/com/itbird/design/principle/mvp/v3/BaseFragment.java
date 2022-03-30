package com.itbird.design.principle.mvp.v3;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by itbird on 2022/3/30
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {
    T mPresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.onAttach((V) this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.onDetach();
            ;
            mPresenter = null;
        }
    }

    abstract T initPresenter();
}
