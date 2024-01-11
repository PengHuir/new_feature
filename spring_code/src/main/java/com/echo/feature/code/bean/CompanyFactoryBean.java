package com.echo.feature.code.bean;

import com.echo.feature.code.entity.Company;
import org.springframework.beans.factory.FactoryBean;

/**
 * Name: CompanyFactoryBean
 * Description: 公司工厂类
 * Author: zhangph
 * Date: 2023/12/29 14:11
 */
public class CompanyFactoryBean implements FactoryBean<Company> {

    private String companyInfo; // 公司名称,地址,规模

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    @Override
    public Company getObject() throws Exception {

        // 模拟创建复杂对象Company
        Company company = new Company();
        String[] strings = companyInfo.split(",");
        company.setName(strings[0]);
        company.setAddress(strings[1]);
        company.setScale(Integer.parseInt(strings[2]));

        return company;
    }

    @Override
    public Class<?> getObjectType() {
        return Company.class;
    }
}
