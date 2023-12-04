package com.echo.feature.entity.VO;

import com.echo.feature.anno.StatusEnumAnno;
import com.echo.feature.enums.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 在线状态
     */
    @StatusEnumAnno(message = "在线状态不能为空")
    @NotNull(message = "在线状态不能为空")
    private StatusEnum status;
}
