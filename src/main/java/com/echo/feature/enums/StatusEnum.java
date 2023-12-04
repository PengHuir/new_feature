package com.echo.feature.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/11/29 09:54
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {

    online(1, "在线"),
    offline(0, "离线");

    @JsonValue
    private Integer code;

    private String desc;
}
