package com.itbird.design.singleton.implmethod.v4;

/**
 * 静态内部类实现方式
 * 单例类
 * Created by itbird on 2022/5/24
 */
public class Singletom {
    /**
     * 因为已经把构造函数私有，必须公开一个静态方法，用于外界获取这个唯一实例
     *
     * @return
     */
    public static Singletom getInstance() {
        return SingletomHolder.mSingletom;
    }

    /**
     * 静态内部类
     */
    private static class SingletomHolder {
        private static final Singletom mSingletom = new Singletom();
    }

    /**
     * 构造函数私有，防止在外部new 实例
     */
    private Singletom() {
    }
}
