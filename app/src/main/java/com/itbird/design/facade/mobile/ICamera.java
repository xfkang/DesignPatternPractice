package com.itbird.design.facade.mobile;

/**
 * 摄像头功能接口
 * Created by itbird on 2022/7/12
 */
public interface ICamera {
    /**
     * 拍照
     */
    void takePhoto();

    /**
     * 录像
     */
    void takeVideo();
}
