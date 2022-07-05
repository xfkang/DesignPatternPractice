package com.itbird.design.proxy;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.design.R;
import com.itbird.design.observer.demo.ObservableImpl;
import com.itbird.design.observer.demo.Observer;
import com.itbird.design.proxy.demo.dynamic.v2.Client;
import com.itbird.design.proxy.retrofit.BaseRequest;
import com.itbird.design.strategy.demo.retrofit.GsonConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;


/**
 * 测试代理模式
 * Created by itbird on 2022/7/4
 */
public class ProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
        tesProxyDynamicPatterm();
        //为了查看retrofit源码
//        testRetrofit();
    }

    /**
     * 为了查看retrofit源码
     */
    private void testRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .addConverterFactory(new Converter.Factory() {
                    //响应数据返回
                    @Override
                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        return super.responseBodyConverter(type, annotations, retrofit);
                    }

                    //请求数据封装
                    @Override
                    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
                        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
                    }
                })
                //baseurl指定
                .baseUrl("xxx")
                .build();
        BaseRequest baseRequest = retrofit.create(BaseRequest.class);
        Call call = baseRequest.getName();
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试代理模式--动态代理
     */
    private void tesProxyDynamicPatterm() {
        Client client = new Client();
        client.main(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

