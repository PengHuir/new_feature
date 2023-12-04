package com.echo.feature.service.impl;

import com.echo.feature.entity.VO.UserVO;
import com.echo.feature.service.IUnifiedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/12/4 17:31
 */
@Slf4j
@Service
public class UnifiedService implements IUnifiedService {

    @Override
    public void save(UserVO userVO) {
        log.debug("save({})", userVO);
    }
}
