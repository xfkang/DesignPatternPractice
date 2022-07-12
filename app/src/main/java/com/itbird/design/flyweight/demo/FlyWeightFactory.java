package com.itbird.design.flyweight.demo;

import java.util.HashMap;

/**
 * 负责管理享元对象池、创建享元对象
 * Created by itbird on 2022/7/11
 */
public class FlyWeightFactory {
    HashMap<String, ConcreateFlyWeight> concreateFlyWeights = new HashMap<>();

    public FlyWeight getFlyWeight(String key) {
        if (concreateFlyWeights.containsKey(key)) {
            return concreateFlyWeights.get(key);
        }
        ConcreateFlyWeight concreateFlyWeight = new ConcreateFlyWeight();
        concreateFlyWeights.put(key, concreateFlyWeight);
        return concreateFlyWeight;
    }
}
