package com.itbird.design.singleton.implmethod.v5;

import java.util.HashMap;
import java.util.Map;

/**
 * 集合单例实现方式
 * 单例类
 * Created by itbird on 2022/5/24
 */
public class Singletom {
    private final static Map<String, Object> map = new HashMap<>();

    static {
        map.put("Singletom", new Singletom());
    }

    /**
     * 因为已经把构造函数私有，必须公开一个静态方法，用于外界获取这个唯一实例
     *
     * @return
     */
    public static Object getInstance(String name) {
        return map.get(name);
    }

    /**
     * 构造函数私有，防止在外部new 实例
     */
    private Singletom() {
    }
}
