package com.echo.feature.boot.controller;

import com.echo.feature.boot.anno.EnumParamAnno;
import com.echo.feature.boot.entity.Res;
import com.echo.feature.boot.entity.VO.UserVO;
import com.echo.feature.boot.enums.StatusEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 *
 * @author ph.zhang
 * Created by on 2023/11/29 09:53
 */
@RestController
@RequestMapping("/enum")
public class EnumController {

    @GetMapping("/list")
    public Res<List<UserVO>> list(@EnumParamAnno(parameterName = "status", valueMethod = "getCode") StatusEnum status) {
        List<UserVO> users = new ArrayList<>();

        UserVO userVO = new UserVO();
        userVO.setId(1L);
        userVO.setName("ph");
        userVO.setStatus(StatusEnum.online);
        users.add(userVO);

        userVO = new UserVO();
        userVO.setId(2L);
        userVO.setName("lj");
        userVO.setStatus(StatusEnum.offline);
        users.add(userVO);

        return Res.success(users);
    }
}
