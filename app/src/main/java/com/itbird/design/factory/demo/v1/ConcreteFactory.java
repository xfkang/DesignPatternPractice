package com.itbird.design.factory.demo.v1;

/**
 * 具体的工厂实现接口
 * Created by itbird on 2022/6/1
 */
public class ConcreteFactory implements IFactroy {
    /**
     * 根据调用者传入的不同产品类型，生成不同的对象
     * @param type
     * @return
     */
    @Override
    public IProduct createProduct(int type) {
        switch (type) {
            case 1:
                return new ConCreteIProductA();
            case 2:
                return new ConCreteIProductB();
            case 3:
                return new ConCreteIProductC();
        }
        return null;
    }
}
