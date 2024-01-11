package com.echo.feature.boot.anno;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Name: RetrofitScans
 * Description:
 * Author: zhangph
 * Date: 2024/1/11 15:37
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RetrofitScannerRegistrar.RepeatingRegistrar.class)
public @interface RetrofitScans {

    RetrofitScan[] value();
}
