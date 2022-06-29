package com.itbird.design.template;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.itbird.design.R;
import com.itbird.design.template.baseactivity.BaseActivity;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;

/**
 * 模板方法模式
 * Created by itbird on 2022/6/15
 */
public class TemplateActivity extends BaseActivity {

    private static final String TAG = TemplateActivity.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_common;
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
        //Rxjava源码中的模板方法模式
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Throwable {
                return null;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testAsyncTask();
            }
        });
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {

    }

    /**
     * AsyncTask源码分析--模板方法的应用
     */
    private void testAsyncTask() {
        MyTask myTask = new MyTask();
        for (int i = 0; i < 10; i++) {
            myTask.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Log.d(TAG, "testAsyncTask running" + Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    class MyTask extends AsyncTask<Integer, Integer, String> {
        private final String TAG = MyTask.class.getSimpleName();

        @Override
        protected String doInBackground(Integer... integers) {
            for (int i : integers) {
                Log.d(TAG, "AsyncTask doInBackground = " + i);
            }
            return null;
        }

        /**
         * 执行前的操作，放在主线程
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "AsyncTask onPreExecute");
        }

        /**
         * 执行后的操作，放在主线程
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "AsyncTask onPostExecute");
        }

        /**
         * 进度回调，放在主线程
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d(TAG, "AsyncTask onProgressUpdate");
        }
    }
}