package com.echo.feature.code;

import com.echo.feature.code.entity.CodeBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Name: CodeBeanApplication
 * Description:
 * Author: zhangph
 * Date: 2023/12/29 14:52
 */
public class CodeBeanApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:codeBean.xml");

        CodeBean bean = context.getBean(CodeBean.class);
        System.out.println(bean);


    }
}
