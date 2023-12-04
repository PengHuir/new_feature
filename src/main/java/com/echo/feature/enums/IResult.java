package com.echo.feature.enums;

/**
 * 定义返回数据结构
 *
 * @author ph.zhang
 * Created by on 2023/12/4 17:33
 */
public interface IResult {

    /**
     * 获取返回码
     *
     * @return 返回码
     */
    Integer getCode();

    /**
     * 获取描述信息
     *
     * @return 描述信息
     */
    String getMessage();
}
