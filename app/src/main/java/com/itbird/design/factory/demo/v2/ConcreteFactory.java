package com.itbird.design.factory.demo.v2;

import com.itbird.design.factory.demo.v1.IProduct;

/**
 * 具体的工厂实现接口
 * Created by itbird on 2022/6/1
 */
public class ConcreteFactory implements IFactroy {
    private ConcreteFactory() {
    }

    public static ConcreteFactory getInstance() {
        return ConcreteFactoryHolder.instance;
    }

    private final static class ConcreteFactoryHolder {
        final static ConcreteFactory instance = new ConcreteFactory();
    }

    /**
     * 根据调用者传入的不同产品类型class，反射生成不同的对象
     *
     * @param tClass
     * @return
     */
    @Override
    public <T extends IProduct> T createProduct(Class<T> tClass) {
        Class cls = null;
        try {
            cls = Class.forName(tClass.getCanonicalName());
            return (T) cls.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
