package com.itbird.design.strategy.demo.money.v2;

/**
 * 策略模式的上下文，用于选择不同的策略
 * Created by itbird on 2022/6/24
 */
public class FinanceContext {
    private IFinance finance;

    public FinanceContext(IFinance finance) {
        this.finance = finance;
    }

    public float finance(int month, int money) {
        return finance.finance(month, money);
    }
}
