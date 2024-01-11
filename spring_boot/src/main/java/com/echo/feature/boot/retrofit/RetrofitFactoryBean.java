package com.echo.feature.boot.retrofit;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.Configuration;
import org.springframework.beans.factory.FactoryBean;

import static org.springframework.util.Assert.notNull;

/**
 * Name: RetrofitFactoryBean
 * Description:
 * Author: zhangph
 * Date: 2024/1/11 16:48
 */
@Slf4j
public class RetrofitFactoryBean<T> extends RetrofitSupport implements FactoryBean<T> {

    private Class<T> retrofitInterface;

    private boolean addToConfig = true;

    public RetrofitFactoryBean() {
    }

    public RetrofitFactoryBean(Class<T> retrofitInterface) {
        this.retrofitInterface = retrofitInterface;
    }

    @Override
    protected void checkRetrofit() throws IllegalArgumentException {
        notNull(this.retrofitInterface, "Property 'retrofitInterface' is required");

        RetrofitRegistry registry = getRegistry();
        if (this.addToConfig && !registry.hasRetrofit(this.retrofitInterface)) {
            try {
                registry.addRetrofit(this.retrofitInterface);
            } catch (Exception e) {
                log.error("Error while adding the mapper '" + this.retrofitInterface + "' to configuration.", e);
                throw new IllegalArgumentException(e);
            }
        }
    }

    @Override
    public T getObject() throws Exception {
        return getRegistry().getRetrofit(this.retrofitInterface);
    }

    @Override
    public Class<?> getObjectType() {
        return this.retrofitInterface;
    }

    public Class<T> getRetrofitInterface() {
        return retrofitInterface;
    }

    public void setRetrofitInterface(Class<T> retrofitInterface) {
        this.retrofitInterface = retrofitInterface;
    }

    public boolean isAddToConfig() {
        return addToConfig;
    }

    public void setAddToConfig(boolean addToConfig) {
        this.addToConfig = addToConfig;
    }
}
