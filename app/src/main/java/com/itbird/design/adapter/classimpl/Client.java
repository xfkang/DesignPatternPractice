package com.itbird.design.adapter.classimpl;

/**
 * Created by itbird on 2022/6/30
 */
public class Client {
    /**
     * 测试1
     */
    public void test() {
        UpgradeModem.getInstance().attachObserver(new UpgradeObserver() {
            @Override
            public void upgradeA(int process) {

            }

            @Override
            public void upgradeB(int process) {

            }

            @Override
            public void upgradeC(int process) {

            }

            @Override
            public void upgradeD(int process) {

            }

            @Override
            public void upgradeE(int process) {

            }

            @Override
            public void upgradeF(int process) {

            }
        });
        UpgradeModem.getInstance().startUpgrade("xxx");
    }

    public void test1() {
        UpgradeModem.getInstance().attachObserver(new UpgradeObserverAdapter() {
            @Override
            public void upgradeA(int process) {
                super.upgradeA(process);
            }
        });
        UpgradeModem.getInstance().startUpgrade("Axxx");
    }
}
