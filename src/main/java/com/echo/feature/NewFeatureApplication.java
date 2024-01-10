package com.echo.feature;

import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.echo.feature.mapper")
@RetrofitScan("com.echo.feature.client")
public class NewFeatureApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewFeatureApplication.class, args);
    }

}
