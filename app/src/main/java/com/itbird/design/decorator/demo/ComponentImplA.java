package com.itbird.design.decorator.demo;

import android.util.Log;

/**
 * 扩展功能类
 * Created by itbird on 2022/6/7
 */
public class ComponentImplA extends DecoratorComponent {
    private static final String TAG = ComponentImplA.class.getSimpleName();

    public ComponentImplA(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        operationA();
        super.operation();
        operationB();
    }

    private void operationB() {
        Log.d(TAG, "ComponentImpl1 operationB");
    }

    private void operationA() {
        Log.d(TAG, "ComponentImpl1 operationA");
    }
}
