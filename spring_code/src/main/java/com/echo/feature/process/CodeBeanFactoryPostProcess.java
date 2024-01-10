package com.echo.feature.process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Name: CodeBeanFactoryPostProcess
 * Description:
 * Author: zhangph
 * Date: 2023/12/29 14:48
 */
public class CodeBeanFactoryPostProcess implements BeanFactoryPostProcessor {

    public CodeBeanFactoryPostProcess() {
        System.out.println("CodeBeanFactoryPostProcess constructor execution ... ");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("CodeBeanFactoryPostProcess postProcessBeanFactory execution ... ");
    }
}
