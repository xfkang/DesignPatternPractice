package com.itbird.design.principle.mvp.v1;

/**
 * 自定义MVP框架，BasePresenter
 * Created by itbird on 2022/2/25
 */
public interface IPresenter {
    /**
     * 与view班定
     *
     * @param view
     */
    void onAttach(IView view);

    /**
     * 与view解绑
     */
    void onDetach();

    /**
     * 是否与view已经班定成功
     *
     * @return
     */
    boolean isViewAttached();

    /**
     * 获取view
     * @return
     */
    IView getView();
}
