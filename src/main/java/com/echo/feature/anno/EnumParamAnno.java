package com.echo.feature.anno;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnumParamAnno {

    String parameterName() default "";

    /**
     * 赋值调用方法
     * 如果为空，默认调用name()方法
     * 该方法必须是一个不含参数的方法，否则将会调用失败
     *
     * @return
     */
    String valueMethod() default "";
}
