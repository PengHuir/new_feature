package com.echo.feature.entity.VO;

import com.echo.feature.enums.StatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/11/29 09:57
 */
@Getter
@Setter
@NoArgsConstructor
public class UserVO {

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 在线状态
     */
    private StatusEnum status;
}
