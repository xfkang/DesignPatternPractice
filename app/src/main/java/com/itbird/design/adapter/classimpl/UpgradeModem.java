package com.itbird.design.adapter.classimpl;

import java.util.ArrayList;
import java.util.List;

/**
 * sdk提供的升级模块单例类，其实只提供了三个接口，一个是升级，两个是注册监听和注销监听
 * Created by itbird on 2022/6/30
 */
public class UpgradeModem {
    List<UpgradeObserver> upgradeObserverList = new ArrayList<>();

    private final static class UpgradeModemHolder {
        private final static UpgradeModem mInstance = new UpgradeModem();
    }

    public static UpgradeModem getInstance() {
        return UpgradeModemHolder.mInstance;
    }

    private UpgradeModem() {

    }

    /**
     * 注册监听
     * @param observer
     */
    public synchronized void attachObserver(UpgradeObserver observer) {
        if (!upgradeObserverList.contains(observer)) {
            upgradeObserverList.add(observer);
        }
    }

    /**
     * 注销监听
     * @param observer
     */
    public synchronized void detachObserver(UpgradeObserver observer) {
        if (upgradeObserverList.contains(observer)) {
            upgradeObserverList.remove(observer);
        }
    }

    /**
     * 升级
     * @param url
     */
    public void startUpgrade(String url) {
        for (UpgradeObserver observer : upgradeObserverList) {
            observer.upgradeA(1);
            // observer.upgradeB(1);
        }
    }

}
