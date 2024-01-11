package com.echo.feature.boot.retrofit;

import com.echo.feature.boot.anno.RetrofitClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Name: RetrofitProxyFactory
 * Description:
 * Author: zhangph
 * Date: 2024/1/11 18:17
 */
public class RetrofitProxyFactory<T> {

    private Class<T> retrofitInterface;

    public RetrofitProxyFactory(Class<T> retrofitInterface) {
        this.retrofitInterface = retrofitInterface;
    }

    public Class<T> getRetrofitInterface() {
        return retrofitInterface;
    }

    private Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://12345676543:8003/")
                .addConverterFactory(ScalarsConverterFactory.create()) // 仅当您需要处理String响应时添加
                .addConverterFactory(GsonConverterFactory.create()) // 仅当您需要处理JSON响应时添加
                .build();
    }

    public T newInstance(Class<T> type) {
        RetrofitClient annotation = type.getAnnotation(RetrofitClient.class);
        Retrofit retrofit = retrofit();
        if (!annotation.baseUrl().isEmpty()) {
            String baseUrl = annotation.baseUrl();
            retrofit = retrofit.newBuilder().baseUrl(baseUrl).build();
        }

        return retrofit.create(type);
    }
}
