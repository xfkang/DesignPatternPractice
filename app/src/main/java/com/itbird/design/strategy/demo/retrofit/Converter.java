package com.itbird.design.strategy.demo.retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * Created by itbird on 2022/6/28
 */
public interface Converter<F, T> {
    T convert(F value) throws IOException;

    /** Creates {@link Converter} instances based on a type and target usage. */
    abstract class Factory {
        /**
         * 响应数据的转换接口，将服务端返回的数据，通过转换工具库，转换为我们需要的数据体
         */
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                                Retrofit retrofit) {
            return null;
        }

        /**
         * 请求数据的转换接口，请请求的裸数据，通过转换数据库，转换为与服务端约定的数据格式
         */
        public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                              Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            return null;
        }

        public Converter<?, String> stringConverter(Type type, Annotation[] annotations,
                                                    Retrofit retrofit) {
            return null;
        }
    }
}

