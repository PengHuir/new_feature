package com.echo.feature.boot.entity.DTO.bill;

import lombok.Data;

import java.io.Serializable;

/**
 * Name: SopAuthCallBackDTO
 * Description:
 * Author: zhangph
 * Date: 2024/1/11 14:33
 */
@Data
public class SopAuthCallBackResDTO implements Serializable {

    /**
     * 流水号
     */
    private String serialNum;
}
