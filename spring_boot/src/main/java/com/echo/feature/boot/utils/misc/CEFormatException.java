package com.echo.feature.boot.utils.misc;

import java.io.IOException;

/**
 * Name: CEFormatException
 * Description: 同 jdk8 sun.misc.CEFormatException
 * Author: zhangph
 * Date: 2024/1/11 11:33
 */
public class CEFormatException extends IOException {
    static final long serialVersionUID = -7139121221067081482L;

    public CEFormatException(String var1) {
        super(var1);
    }
}
