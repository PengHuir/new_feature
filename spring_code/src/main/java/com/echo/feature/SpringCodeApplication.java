package com.echo.feature;

import com.echo.feature.service.ISpringCodeService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Name: SpringCodeApplication
 * Description:
 * Author: zhangph
 * Date: 2023/12/29 11:32
 */
public class SpringCodeApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring.xml");
        context.start();

        ISpringCodeService bean = context.getBean(ISpringCodeService.class);
        bean.test();

        Object company = context.getBean("companyFactoryBean");
        System.out.println(company + "\t" + company.getClass());

        Object companyFactoryBean = context.getBean("&companyFactoryBean");
        System.out.println(companyFactoryBean + "\t" + companyFactoryBean.getClass());

        context.close();
    }
}
