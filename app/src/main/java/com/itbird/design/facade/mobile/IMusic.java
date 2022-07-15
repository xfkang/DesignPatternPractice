package com.itbird.design.facade.mobile;

/**
 * 音乐功能接口
 * Created by itbird on 2022/7/12
 */
public interface IMusic {
    /**
     * 播放
     */
    void play(String musicPath);

    /**
     * 暂停
     */
    void pause(String musicPath);
}
