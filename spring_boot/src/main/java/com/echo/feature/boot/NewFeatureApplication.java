package com.echo.feature.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.echo.feature.boot.mapper")
public class NewFeatureApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewFeatureApplication.class, args);
    }

}
