package com.itbird.design.facade.mobile;

/**
 * 电话功能接口
 * Created by itbird on 2022/7/12
 */
public interface Iphone {
    /**
     * 打电话
     * @param phoneNum
     */
    void call(String phoneNum);

    /**
     * 挂断电话
     */
    void unCall();
}
