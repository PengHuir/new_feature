package com.echo.feature;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = NewFeatureApplication.class)
public class NewFeatureApplicationTests {

    @Test
    public void contextLoads() {
    }
}
