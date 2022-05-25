package com.itbird.design.singleton.implmethod.v3;

/**
 * DCL实现方式
 * 单例类
 * Created by itbird on 2022/5/24
 */
public class Singletom {
    private volatile static Singletom mSingletom;

    /**
     * 因为已经把构造函数私有，必须公开一个静态方法，用于外界获取这个唯一实例
     *
     * @return
     */
    public static Singletom getInstance() {
        if (mSingletom == null) {//2
            synchronized (Singletom.class) {
                if (mSingletom == null) {
                    mSingletom = new Singletom();//1
                }
            }
        }
        return mSingletom;
    }

    /**
     * 构造函数私有，防止在外部new 实例
     */
    private Singletom() {
    }
}
