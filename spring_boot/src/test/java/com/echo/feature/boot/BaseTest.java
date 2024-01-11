package com.echo.feature.boot;

import com.echo.feature.boot.NewFeatureApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/12/19 11:00
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = NewFeatureApplication.class)
@Transactional
@Rollback
public class BaseTest {
}
