package com.echo.feature.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/11/29 10:02
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Res<T> {

    /**
     * 状态码
     */
    private int code;

    /**
     * 返回描述信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public static <T> Res<T> success(T data) {
        return success("success", data);
    }

    public static <T> Res<T> success(String message, T data) {
        return new Res<>(0, message, data);
    }

    public static <T> Res<T> fail(int code, String message) {
        return new Res<>(code, message, null);
    }
}