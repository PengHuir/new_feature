package com.echo.feature.boot.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Name: DynamicUrl
 * Description: retrofit 动态url；应用于接口类型和方法类型
 * Author: zhangph
 * Date: 2024/1/11 11:44
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD}) // 应用于接口和方法
public @interface DynamicUrl {
    String value() default "";
}
