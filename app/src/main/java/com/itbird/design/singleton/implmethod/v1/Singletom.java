package com.itbird.design.singleton.implmethod.v1;

/**
 * 饿汉实现方式
 * 单例类
 * Created by itbird on 2022/5/24
 */
public class Singletom {
    private static Singletom mSingletom = new Singletom();

    /**
     * 因为已经把构造函数私有，必须公开一个静态方法，用于外界获取这个唯一实例
     *
     * @return
     */
    public static Singletom getInstance() {
        return mSingletom;
    }

    /**
     * 构造函数私有，防止在外部new 实例
     */
    private Singletom() {
    }
}
