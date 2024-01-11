package com.echo.feature.boot.service;

import com.echo.feature.boot.entity.VO.UserVO;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/12/4 17:31
 */
public interface IUnifiedService {

    /**
     * 存储
     * @param userVO
     */
    void save(UserVO userVO);
}
