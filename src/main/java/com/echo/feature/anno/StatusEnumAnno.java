package com.echo.feature.anno;

import com.echo.feature.validator.StatusEnumValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/12/4 17:59
 */
//自定义注解类
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = StatusEnumValidator.class)
public @interface StatusEnumAnno {

    /**
     * 是否允许为空
     */
    boolean required() default true;

    /**
     * 校验不通过返回的提示信息
     */
    String message() default "不是一个手机号码格式";

    /**
     * Constraint要求的属性，用于分组校验和扩展，留空就好
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
