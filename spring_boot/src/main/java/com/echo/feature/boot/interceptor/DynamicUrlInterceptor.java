package com.echo.feature.boot.interceptor;

import com.echo.feature.boot.anno.DynamicUrl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Name: DynamicUrlInterceptor
 * Description: 获取DynamicUrl注解的拦截器
 * Author: zhangph
 * Date: 2024/1/11 11:46
 */
public class DynamicUrlInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl originalHttpUrl = request.url();


        // 获取调用方法的注解
        /*Method method = ... // 从chain获取当前的请求方法
        DynamicUrl methodAnnotation = method.getAnnotation(DynamicUrl.class);

        String dynamicUrlValue = null;
        if (methodAnnotation != null) {
            dynamicUrlValue = methodAnnotation.value();
        } else {
            // 方法没有注解，尝试从接口获取
            Class<?> declaringClass = method.getDeclaringClass();
            DynamicUrl classAnnotation = declaringClass.getAnnotation(DynamicUrl.class);
            if (classAnnotation != null) {
                dynamicUrlValue = classAnnotation.value();
            }
        }

        if (dynamicUrlValue != null) {
            // 构建新的URL并发出请求
            HttpUrl newUrl = HttpUrl.parse(dynamicUrlValue);
            Request newRequest = request.newBuilder()
                    .url(newUrl)
                    .build();
            return chain.proceed(newRequest);
        }*/

        return chain.proceed(request);
    }
}
