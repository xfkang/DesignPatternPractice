package com.itbird.design;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.design.adapter.AdapterActivity;
import com.itbird.design.builder.BuilderActivity;
import com.itbird.design.chaiin.ChainActivity;
import com.itbird.design.decorator.DecoratorActivity;
import com.itbird.design.factory.FactoryActivity;
import com.itbird.design.observer.ObserverActivity;
import com.itbird.design.principle.PrincipleActivity;
import com.itbird.design.proxy.ProxyActivity;
import com.itbird.design.singleton.activitymanager.SingletonActivity;
import com.itbird.design.strategy.StrategyActivity;
import com.itbird.design.template.TemplateActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 测试装饰者模式
     */
    public void testDecoratorPatterm(View view) {
        startActivity(DecoratorActivity.class);
    }


    /**
     * 测试模板方法模式
     * @param view
     */
    public void testTemplatePatterm(View view) {
        startActivity(TemplateActivity.class);
    }
    /**
     * 测试工厂模式
     */
    public void testFactoryPatterm(View view) {
        startActivity(FactoryActivity.class);
    }

    /**
     * 测试建造者模式
     */
    public void testBuilderPatterm(View view) {
        startActivity(BuilderActivity.class);

    }


    /**
     * 测试单例模式，activitymanager实现
     */
    public void testSingletomPatterm(View view) {
        startActivity(SingletonActivity.class);
    }

    /**
     * 测试六大基本原则
     */
    public void testPrinciple(View view) {
        startActivity(PrincipleActivity.class);
    }


    /**
     * 测试观察者模式
     */
    public void testObserverPatterm(View view) {
        startActivity(ObserverActivity.class);
    }

    /**
     * 测试责任链模式
     */
    public void testChainPatterm(View view) {
        startActivity(ChainActivity.class);
    }

    private void startActivity(Class<?> targetclass) {
        startActivity(new Intent(this, targetclass));
    }

    /**
     * 测试策略模式
     */
    public void testStrategyPatterm(View view) {
        startActivity(StrategyActivity.class);
    }

    /**
     * 测试适配器模式
     * @param view
     */
    public void testAdapterPatterm(View view) {
        startActivity(AdapterActivity.class);
    }

    public void testProxyPatterm(View view) {
        startActivity(ProxyActivity.class);
    }
}