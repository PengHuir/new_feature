package com.echo.feature.exception;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/12/4 18:34
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
