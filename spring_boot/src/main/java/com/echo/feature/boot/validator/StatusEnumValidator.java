package com.echo.feature.boot.validator;

import cn.hutool.core.util.StrUtil;
import com.echo.feature.boot.anno.StatusEnumAnno;
import com.echo.feature.boot.enums.StatusEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/12/4 18:01
 */
public class StatusEnumValidator implements ConstraintValidator<StatusEnumAnno, CharSequence> {

    private boolean required = false;

    /**
     * 在验证开始前调用注解里的方法，从而获取到一些注解里的参数
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(StatusEnumAnno constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }


    /**
     * 判断参数是否合法
     *
     * @param charSequence
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (this.required) {
            // 验证
            return isStatusEnum(charSequence);
        }
        if (StrUtil.isBlank(charSequence)) {
            // 验证
            return isStatusEnum(charSequence);
        }
        return true;
    }

    private boolean isStatusEnum(CharSequence charSequence) {
        if (StrUtil.isBlank(charSequence)) {
            return false;
        }
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.getCode().equals(Integer.parseInt(charSequence.toString()))) {
                return true;
            }
        }
        return false;
    }
}
