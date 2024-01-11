package com.echo.feature.code.entity;

import org.springframework.beans.factory.InitializingBean;

/**
 * Name: CodeBean
 * Description: 测试Code加载
 * Author: zhangph
 * Date: 2023/12/29 14:44
 */
public class CodeBean implements InitializingBean {

    public CodeBean() {
        System.out.println("CodeBean constructor execution ... ");
    }

    public void init() {
        System.out.println("CodeBean init execution ... ");
    }

    public void destroy() {
        System.out.println("CodeBean destroy execution ... ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean afterPropertiesSet execution ... ");
    }
}
