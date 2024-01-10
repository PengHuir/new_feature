package com.echo.feature.service.impl;

import com.echo.feature.service.ISpringCodeService;
import com.echo.feature.service.dao.SpringCodeMapper;
import lombok.Getter;
import lombok.Setter;

/**
 * Name: SpringCodeService
 * Description:
 * Author: zhangph
 * Date: 2023/12/29 11:36
 */
@Setter
@Getter
public class SpringCodeService implements ISpringCodeService {

    private SpringCodeMapper springCodeMapper;

    @Override
    public void test() {
        springCodeMapper.test();
    }
}
