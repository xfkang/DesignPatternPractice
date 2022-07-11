package com.itbird.design.strategy;

import static com.itbird.design.strategy.view.MyView.RADIUS;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PointFEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.itbird.design.BuildConfig;
import com.itbird.design.R;
import com.itbird.design.strategy.demo.compute.v1.Compute;
import com.itbird.design.strategy.demo.compute.v2.ComputeADD;
import com.itbird.design.strategy.demo.compute.v2.Context;
import com.itbird.design.strategy.demo.money.v1.FinanceManagerV1;
import com.itbird.design.strategy.demo.money.v2.FinanceContext;
import com.itbird.design.strategy.demo.money.v2.YuEBaoFinance;
import com.itbird.design.strategy.view.MyView;
import com.itbird.design.template.baseactivity.BaseActivity;

import org.xmlpull.v1.XmlPullParserFactory;

import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * 策略模式
 * Created by itbird on 2022/6/20
 */
public class StrategyActivity extends BaseActivity {

    private static final String TAG = StrategyActivity.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_strategy;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    /**
     * 钩子函数，子类实现，用来控制父类中，某个方法是否执行
     *
     * @return
     */
    @Override
    public boolean isInitData() {
        return false;
    }

    @Override
    protected void initView() {
        //Demo测试
        testDemo();
        //Timeer相关
        testTimer();
        //属性动画相关
        testObjectAnimation();
        //retrofit相关
        testRetrofit();
        //测试MyView
        testMyView();
    }

    private void testMyView() {
        //起点和终点
        PointF startPoint = new PointF(RADIUS, RADIUS);
        PointF endPoint = new PointF(getWindowManager().getDefaultDisplay().getWidth() - RADIUS, getWindowManager().getDefaultDisplay().getHeight() - RADIUS);
        //自定义view加载，设置起点和终点
        MyView myView = findViewById(R.id.myview);
        myView.setStartPoint(startPoint);
        myView.setEndPoint(endPoint);
        Log.d(TAG, "MyView set end");
        /**
         * 开始值动画, 注意，此处值动画是作用于PointF对象
         */
        ValueAnimator animator = ValueAnimator.ofObject(new PointFEvaluator(), startPoint, endPoint);
        animator.setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF currentPoint = (PointF) animation.getAnimatedValue();
                myView.updatePoint(currentPoint);
            }
        });
        animator.start();
    }

    /**
     * retrofit测试相关
     */
    private void testRetrofit() {
//        Retrofit mRetrofit = new Retrofit.Builder()
//                //关键这里，可以提供各种转换库，当然也可以用自定义的转换库
//                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(SimpleXmlConverterFactory.create())
//                .baseUrl("")
//                .build();
    }

    /**
     * Demo测试
     */
    private void testDemo() {

        //V1调用方式
        FinanceManagerV1 financeManagerV1 = new FinanceManagerV1();
        float money = financeManagerV1.finance(3, 10000, FinanceManagerV1.TYPE_YUEBAO);
        System.out.println("money = " + money);
        //V2调用方法
        FinanceContext context = new FinanceContext(new YuEBaoFinance());
        context.finance(3, 10000);

        Log.d(TAG, "A+B=" + Compute.compute(1, 22, 22));
        Log.d(TAG, "A-B=" + Compute.compute(2, 22, 22));
        Log.d(TAG, "A*B=" + Compute.compute(3, 22, 22));
        Log.d(TAG, "A/B=" + Compute.compute(4, 22, 22));

        Context cc = new Context(new ComputeADD());
        Log.d(TAG, "A+...=" + cc.compute(new Double[]{1.0, 2.0, 3.0, 4.0, 5.0}));
    }

    /**
     * 属性动画
     */
    private void testObjectAnimation() {
        //ObjectAnimator源码分析，内部使用策略模式
        ImageView imageView = findViewById(R.id.imageview);
        //为imageview设置旋转动画
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, ImageView.ROTATION, 0f, 360f);
        //设置匀速执行
        animator.setInterpolator(new LinearInterpolator());
        //设置估值器
        animator.setEvaluator(new FloatEvaluator());
        //设置动画持续时间为3s
        animator.setDuration(3000);
        // 设置重复播放动画模式
        animator.setRepeatMode(ValueAnimator.RESTART);
        //开始动画
        animator.start();

        /**
         * ValueAnimator的实现方式
         */
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 360f);
        valueAnimator.setDuration(3000);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                imageView.setRotation(value);
            }
        });
        valueAnimator.start();
    }

    /**
     * Timer策略测试
     */
    private void testTimer() {
        Timber.tag(TAG);
        if (BuildConfig.DEBUG) {
//            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
        Timber.d("initView");
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {

    }
}