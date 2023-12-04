package com.echo.feature.exception;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/12/4 18:33
 */
public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message) {
        super(message);
    }
}
