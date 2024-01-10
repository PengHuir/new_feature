package com.echo.feature.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/12/19 15:54
 */
@Configuration
public class MyBatisPlusConfig {

    /**
     * 分表插件配置
     * @return 分靠插件
     */
    @Bean
    public ShareInterceptor shareInterceptor() {
        return new ShareInterceptor();
    }

    /**
     * 分页查询配置
     * @return 分页插件
     */
    @Bean
    public PaginationInnerInterceptor paginationInterceptor() {
        return new PaginationInnerInterceptor();
    }
}
