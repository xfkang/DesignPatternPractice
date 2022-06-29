package com.itbird.design.strategy.demo.money.v1;

/**
 * Created by itbird on 2022/6/24
 */
public class FinanceManagerV1 {
    public final static int TYPE_YUEBAO = 1;
    public final static int TYPE_LICAITONG = 2;

    /**
     * 获取计算金额
     *
     * @param month
     * @param money
     * @param type
     * @return
     */
    public float finance(int month, int money, int type) {
        switch (type) {
            case TYPE_YUEBAO:
                return yuebaoFinance(month, money);
            case TYPE_LICAITONG:
                return caifutongFinance(month, money);
            default:
                return 0f;
        }
    }

    private float yuebaoFinance(int month, int money) {
        if (month == 3) {
            return money + money * 0.047f / 12 * month;
        }
        if (month == 6) {
            return money + money * 0.05f / 12 * month;
        }
        if (month == 12) {
            return money + money * 0.06f / 12 * month;
        }
        throw new IllegalArgumentException("月份不对");
    }

    private float caifutongFinance(int month, int money) {
        if (month == 3) {
            return money + money * 0.09f / 12 * month;
        }
        if (month == 6) {
            return money + money * 0.112f / 12 * month;
        }
        if (month == 12) {
            return money + money * 0.12f / 12 * month;
        }
        throw new IllegalArgumentException("月份不对");
    }
}
