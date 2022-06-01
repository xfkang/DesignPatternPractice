package com.itbird.design.builder.demo;

/**
 * 定义builder接口，便于后续扩展
 * Created by itbird on 2022/5/31
 */
public interface BuilderInterface {
    /**
     * 构建A part
     *
     * @param a
     * @return
     */
    BuilderInterface buildApart(String a);

    /**
     * 构建B part
     *
     * @param b
     * @return
     */
    BuilderInterface buildBpart(String b);

    /**
     * 构建C part
     *
     * @param c
     * @return
     */
    BuilderInterface buildCpart(String c);

    Product create();
}
