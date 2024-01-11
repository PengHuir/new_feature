package com.echo.feature.boot.retrofit;

import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Optional;

/**
 * Name: RetrofitScannerConfigurer
 * Description:
 * Author: zhangph
 * Date: 2024/1/11 15:48
 */
public class RetrofitScannerConfigurer implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware, BeanNameAware {

    private String basePackage;

    private boolean addToConfig = true;

    private String lazyInitialization;

    private Class<? extends Annotation> annotationClass;

    private Class<?> markerInterface;

    private Class<? extends RetrofitFactoryBean> retrofitFactoryBeanClass;

    private ApplicationContext applicationContext;

    private String beanName;

    private boolean processPropertyPlaceHolders;

    /**
     * This property lets you set the base package for your mapper interface files.
     * <p>
     * You can set more than one package by using a semicolon or comma as a separator.
     * <p>
     * Mappers will be searched for recursively starting in the specified package(s).
     *
     * @param basePackage
     *          base package name
     */
    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    /**
     * Specifies a flag that whether execute a property placeholder processing or not.
     * <p>
     * The default is {@literal false}. This means that a property placeholder processing does not execute.
     *
     * @param processPropertyPlaceHolders a flag that whether execute a property placeholder processing or not
     * @since 1.1.1
     */
    public void setProcessPropertyPlaceHolders(boolean processPropertyPlaceHolders) {
        this.processPropertyPlaceHolders = processPropertyPlaceHolders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    /**
     * Set whether enable lazy initialization for mapper bean.
     * <p>
     * Default is {@code false}.
     * </p>
     *
     * @param lazyInitialization Set the @{code true} to enable
     * @since 2.0.2
     */
    public void setLazyInitialization(String lazyInitialization) {
        this.lazyInitialization = lazyInitialization;
    }

    /**
     * This property specifies the annotation that the scanner will search for.
     * <p>
     * The scanner will register all interfaces in the base package that also have the specified annotation.
     * <p>
     * Note this can be combined with markerInterface.
     *
     * @param annotationClass annotation class
     */
    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public void setMarkerInterface(Class<?> markerInterface) {
        this.markerInterface = markerInterface;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        if (this.processPropertyPlaceHolders) {
            processPropertyPlaceHolders();
        }

        ClassPathRetrofitScanner scanner = new ClassPathRetrofitScanner(registry);
        scanner.setAddToConfig(this.addToConfig);
        scanner.setAnnotationClass(this.annotationClass);
        scanner.setMarkerInterface(this.markerInterface);
        scanner.setResourceLoader(this.applicationContext);
        scanner.setRetrofitFactoryBeanClass(this.retrofitFactoryBeanClass);
        if (StringUtils.hasText(lazyInitialization)) {
            scanner.setLazyInitialization(Boolean.valueOf(lazyInitialization));
        }

        scanner.registerFilters();
        scanner.scan(
                StringUtils.tokenizeToStringArray(this.basePackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
    }

    /*
     * BeanDefinitionRegistries are called early in application startup, before BeanFactoryPostProcessors. This means that
     * PropertyResourceConfigurers will not have been loaded and any property substitution of this class' properties will
     * fail. To avoid this, find any PropertyResourceConfigurers defined in the context and run them on this class' bean
     * definition. Then update the values.
     */
    private void processPropertyPlaceHolders() {
        Map<String, PropertyResourceConfigurer> prcs = applicationContext.getBeansOfType(PropertyResourceConfigurer.class,
                false, false);

        if (!prcs.isEmpty() && applicationContext instanceof ConfigurableApplicationContext) {
            BeanDefinition mapperScannerBean = ((ConfigurableApplicationContext) applicationContext).getBeanFactory()
                    .getBeanDefinition(beanName);

            // PropertyResourceConfigurer does not expose any methods to explicitly perform
            // property placeholder substitution. Instead, create a BeanFactory that just
            // contains this mapper scanner and post process the factory.
            DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
            factory.registerBeanDefinition(beanName, mapperScannerBean);

            for (PropertyResourceConfigurer prc : prcs.values()) {
                prc.postProcessBeanFactory(factory);
            }

            PropertyValues values = mapperScannerBean.getPropertyValues();

            this.basePackage = getPropertyValue("basePackage", values);
            this.lazyInitialization = getPropertyValue("lazyInitialization", values);
        }

        this.basePackage = Optional.ofNullable(this.basePackage).map(getEnvironment()::resolvePlaceholders).orElse(null);
        this.lazyInitialization = Optional.ofNullable(this.lazyInitialization).map(getEnvironment()::resolvePlaceholders)
                .orElse(null);
    }

    private Environment getEnvironment() {
        return this.applicationContext.getEnvironment();
    }

    private String getPropertyValue(String propertyName, PropertyValues values) {
        PropertyValue property = values.getPropertyValue(propertyName);

        if (property == null) {
            return null;
        }

        Object value = property.getValue();

        if (value == null) {
            return null;
        } else if (value instanceof String) {
            return value.toString();
        } else if (value instanceof TypedStringValue) {
            return ((TypedStringValue) value).getValue();
        } else {
            return null;
        }
    }
}
