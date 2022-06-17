package com.itbird.design.decorator.demo;

/**
 * 组件的装饰者
 * Created by itbird on 2022/6/7
 */
public abstract class DecoratorComponent extends Component {
    Component component;

    public DecoratorComponent(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}
