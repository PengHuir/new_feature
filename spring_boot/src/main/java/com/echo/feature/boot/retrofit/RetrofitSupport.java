package com.echo.feature.boot.retrofit;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;

/**
 * Name: RetrofitSupport
 * Description:
 * Author: zhangph
 * Date: 2024/1/11 18:30
 */
public abstract class RetrofitSupport implements InitializingBean {

    protected final RetrofitRegistry retrofitRegistry = new RetrofitRegistry();

    public final void afterPropertiesSet() throws IllegalArgumentException, BeanInitializationException {
        this.checkRetrofit();

        try {
            this.initRetrofit();
        } catch (Exception var2) {
            throw new BeanInitializationException("Initialization of DAO failed", var2);
        }
    }

    protected RetrofitRegistry getRegistry() {
        return this.retrofitRegistry;
    }

    protected abstract void checkRetrofit() throws IllegalArgumentException;

    protected void initRetrofit() throws Exception {
    }
}
