package com.itbird.design.builder.demo;

/**
 * 我们想要去构建的复杂对象
 * Created by itbird on 2022/5/25
 */
public class Product {
    protected Product(Builder builder) {
        //TODO: 使用builder的各部分零件，然后去生成不同的产品
        //TODO: 可以根据设置的不同值，哪些值设置了，去选择不同的布局，从而可以生成不同样式的view，但是是同一类别的
    }

    /**
     * 构建过程和组装过程
     */
    public static class Builder implements BuilderInterface {
        String a;
        String b;
        String c;

        @Override
        public Builder buildApart(String a) {
            this.a = a;
            return this;
        }

        @Override
        public Builder buildBpart(String b) {
            this.b = b;
            return this;
        }

        @Override
        public Builder buildCpart(String c) {
            this.c = c;
            return this;
        }

        @Override
        public Product create() {
            return new Product(this);
        }
    }
}
