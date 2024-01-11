package com.echo.feature.code.process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Name: CodeBeanPostProcess
 * Description:
 * Author: zhangph
 * Date: 2023/12/29 14:46
 */
public class CodeBeanPostProcess implements BeanPostProcessor {

    public CodeBeanPostProcess() {
        System.out.println("CodeBeanPostProcess constructor execution ... ");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CodeBeanPostProcess postProcessBeforeInitialization execution ... ");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CodeBeanPostProcess postProcessAfterInitialization execution ... ");
        return bean;
    }
}
