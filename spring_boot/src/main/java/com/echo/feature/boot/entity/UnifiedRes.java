package com.echo.feature.boot.entity;

import com.echo.feature.boot.enums.IResult;
import com.echo.feature.boot.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回数据结构
 *
 * @author ph.zhang
 * Created by on 2023/12/4 17:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnifiedRes<T> {

    private Integer code;
    private String message;
    private T data;

    public static <T> UnifiedRes<T> success(T data) {
        return new UnifiedRes<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    public static <T> UnifiedRes<T> success(String message, T data) {
        return new UnifiedRes<>(ResultEnum.SUCCESS.getCode(), message, data);
    }

    public static UnifiedRes<?> failed() {
        return new UnifiedRes<>(ResultEnum.COMMON_FAILED.getCode(), ResultEnum.COMMON_FAILED.getMessage(), null);
    }

    public static UnifiedRes<?> failed(String message) {
        return new UnifiedRes<>(ResultEnum.COMMON_FAILED.getCode(), message, null);
    }

    public static UnifiedRes<?> failed(Integer code, String message) {
        return new UnifiedRes<>(code, message, null);
    }

    public static UnifiedRes<?> failed(IResult errorResult) {
        return new UnifiedRes<>(errorResult.getCode(), errorResult.getMessage(), null);
    }

    public static <T> UnifiedRes<T> instance(Integer code, String message, T data) {
        UnifiedRes<T> result = new UnifiedRes<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
