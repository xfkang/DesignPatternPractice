package com.itbird.design.facade.mobile;

/**
 * 手机系统
 * Created by itbird on 2022/7/12
 */
public class Mobile {
    IMusic music;
    Iphone iphone;
    ICamera camera;

    public Mobile() {
        music = new Music();
        iphone = new Phone();
        camera = new Camera();
    }

    public void takePhoto() {
        camera.takePhoto();
    }

    public void callPhone(String phoneNum) {
        iphone.call(phoneNum);
    }
}
