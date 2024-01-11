package com.echo.feature.boot.anno;

import com.echo.feature.boot.retrofit.RetrofitFactoryBean;
import com.echo.feature.boot.retrofit.RetrofitScannerConfigurer;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Name: RetrofitScannerRegistrar
 * Description:
 * Author: zhangphø
 * Date: 2024/1/11 14:58
 */
public class RetrofitScannerRegistrar implements ImportBeanDefinitionRegistrar {

    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes retrofitScanAttrs = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(RetrofitScan.class.getName()));
        if (retrofitScanAttrs != null) {
            registerBeanDefinitions(importingClassMetadata, retrofitScanAttrs, registry,
                    generateBaseBeanName(importingClassMetadata, 0));
        }
    }

    void registerBeanDefinitions(AnnotationMetadata annoMeta, AnnotationAttributes annoAttrs, BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(RetrofitScannerConfigurer.class);
        builder.addPropertyValue("processPropertyPlaceHolders", annoAttrs.getBoolean("processPropertyPlaceHolders"));
        Class<? extends Annotation> annotationClass = annoAttrs.getClass("annotationClass");
        if (!Annotation.class.equals(annotationClass)) {
            builder.addPropertyValue("annotationClass", annotationClass);
        }

        Class<?> markerInterface = annoAttrs.getClass("markerInterface");
        if (!Class.class.equals(markerInterface)) {
            builder.addPropertyValue("markerInterface", markerInterface);
        }

        Class<? extends RetrofitFactoryBean> retrofitFactoryBeanClass = annoAttrs.getClass("factoryBean");
        if (!RetrofitFactoryBean.class.equals(retrofitFactoryBeanClass)) {
            builder.addPropertyValue("retrofitFactoryBeanClass", retrofitFactoryBeanClass);
        }

        List<String> basePackages = new ArrayList();
        basePackages.addAll((Collection) Arrays.stream(annoAttrs.getStringArray("basePackages")).filter(StringUtils::hasText).collect(Collectors.toList()));
        basePackages.addAll((Collection) Arrays.stream(annoAttrs.getClassArray("basePackageClasses")).map(ClassUtils::getPackageName).collect(Collectors.toList()));
        if (basePackages.isEmpty()) {
            basePackages.add(getDefaultBasePackage(annoMeta));
        }

        builder.addPropertyValue("basePackage", StringUtils.collectionToCommaDelimitedString(basePackages));
        builder.setRole(2);
        registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
    }

    private static String getDefaultBasePackage(AnnotationMetadata importingClassMetadata) {
        return ClassUtils.getPackageName(importingClassMetadata.getClassName());
    }

    static class RepeatingRegistrar extends RetrofitScannerRegistrar {
        /**
         * {@inheritDoc}
         */
        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            AnnotationAttributes retrofitScansAttrs = AnnotationAttributes
                    .fromMap(importingClassMetadata.getAnnotationAttributes(RetrofitScans.class.getName()));
            if (retrofitScansAttrs != null) {
                AnnotationAttributes[] annotations = retrofitScansAttrs.getAnnotationArray("value");
                for (int i = 0; i < annotations.length; i++) {
                    registerBeanDefinitions(importingClassMetadata, annotations[i], registry,
                            generateBaseBeanName(importingClassMetadata, i));
                }
            }
        }
    }

    private static String generateBaseBeanName(AnnotationMetadata importingClassMetadata, int index) {
        String var10000 = importingClassMetadata.getClassName();
        return var10000 + "#" + RetrofitScannerRegistrar.class.getSimpleName() + "#" + index;
    }
}
