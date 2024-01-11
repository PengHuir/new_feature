package com.echo.feature.boot.config;

import com.echo.feature.boot.entity.UnifiedRes;
import com.echo.feature.boot.enums.ResultEnum;
import com.echo.feature.boot.exception.BusinessException;
import com.echo.feature.boot.exception.ForbiddenException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/12/4 18:34
 */
@RestControllerAdvice(basePackages = "com.echo.feature")
public class ExceptionAdvice {

    /**
     * 捕获 {@code BusinessException} 异常
     */
    @ExceptionHandler({BusinessException.class})
    public UnifiedRes<?> handleBusinessException(BusinessException ex) {
        return UnifiedRes.failed(ex.getMessage());
    }

    /**
     * 捕获 {@code ForbiddenException} 异常
     */
    @ExceptionHandler({ForbiddenException.class})
    public UnifiedRes<?> handleForbiddenException(ForbiddenException ex) {
        return UnifiedRes.failed(ResultEnum.FORBIDDEN);
    }

    /**
     * {@code @RequestBody} 参数校验不通过时抛出的异常处理
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public UnifiedRes<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.toString();
        if (StringUtils.hasText(msg)) {
            return UnifiedRes.failed(ResultEnum.VALIDATE_FAILED.getCode(), msg);
        }
        return UnifiedRes.failed(ResultEnum.VALIDATE_FAILED);
    }

    /**
     * {@code @PathVariable} 和 {@code @RequestParam} 参数校验不通过时抛出的异常处理
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public UnifiedRes<?> handleConstraintViolationException(ConstraintViolationException ex) {
        if (StringUtils.hasText(ex.getMessage())) {
            return UnifiedRes.failed(ResultEnum.VALIDATE_FAILED.getCode(), ex.getMessage());
        }
        return UnifiedRes.failed(ResultEnum.VALIDATE_FAILED);
    }

    /**
     * 顶级异常捕获并统一处理，当其他异常无法处理时候选择使用
     */
    @ExceptionHandler({Exception.class})
    public UnifiedRes<?> handle(Exception ex) {
        return UnifiedRes.failed(ex.getMessage());
    }
}
