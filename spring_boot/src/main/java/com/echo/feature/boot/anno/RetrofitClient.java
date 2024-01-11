package com.echo.feature.boot.anno;

import java.lang.annotation.*;

/**
 * Name: Retrofit
 * Description:
 * Author: zhangph
 * Date: 2024/1/11 16:11
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RetrofitClient {

    String baseUrl() default "";
}
