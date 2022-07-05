package com.itbird.design.proxy.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Retrofit请求接口
 * Created by itbird on 2022/7/5
 */
public interface BaseRequest {
    @GET("/getname")
    Call getName();

    @POST("/updatename")
    Call setName(@Query("name") String name);
}
