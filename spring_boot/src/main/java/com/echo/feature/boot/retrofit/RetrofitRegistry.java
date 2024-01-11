package com.echo.feature.boot.retrofit;

import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.binding.MapperProxyFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Name: Retrofit
 * Description:
 * Author: zhangph
 * Date: 2024/1/11 18:24
 */
public class RetrofitRegistry {

    private final Map<Class<?>, RetrofitProxyFactory<?>> knownRetrofits = new ConcurrentHashMap<>();


    public <T> boolean hasRetrofit(Class<T> type) {
        return knownRetrofits.containsKey(type);
    }


    public <T> void addRetrofit(Class<T> type) {
        if (type.isInterface()) {
            if (hasRetrofit(type)) {
                throw new BindingException("Type " + type + " is already known to the RetrofitRegistry.");
            }
            boolean loadCompleted = false;
            try {
                knownRetrofits.put(type, new RetrofitProxyFactory<>(type));
                loadCompleted = true;
            } finally {
                if (!loadCompleted) {
                    knownRetrofits.remove(type);
                }
            }
        }
    }

    public <T> T getRetrofit(Class<T> type) {
        final RetrofitProxyFactory<T> retrofitProxyFactory = (RetrofitProxyFactory<T>) knownRetrofits.get(type);
        if (retrofitProxyFactory == null) {
            throw new BindingException("Type " + type + " is not known to the RetrofitRegistry.");
        }
        try {
            return retrofitProxyFactory.newInstance(type);
        } catch (Exception e) {
            throw new BindingException("Error getting mapper instance. Cause: " + e, e);
        }
    }
}
