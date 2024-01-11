package com.echo.feature.boot.retrofit;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.springframework.aop.scope.ScopedProxyFactoryBean;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

/**
 * Name: ClassPathRetrofitScanner
 * Description:
 * Author: zhangph
 * Date: 2024/1/11 15:51
 */
@Slf4j
public class ClassPathRetrofitScanner extends ClassPathBeanDefinitionScanner {

    // Copy of FactoryBean#OBJECT_TYPE_ATTRIBUTE which was added in Spring 5.2
    static final String FACTORY_BEAN_OBJECT_TYPE = "factoryBeanObjectType";

    private boolean addToConfig = true;

    private boolean lazyInitialization;

    private boolean printWarnLogIfNotFoundRetrofits = true;

    private Class<? extends Annotation> annotationClass;

    private Class<?> markerInterface;

    private Class<? extends RetrofitFactoryBean> retrofitFactoryBeanClass = RetrofitFactoryBean.class;

    public ClassPathRetrofitScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public void setAddToConfig(boolean addToConfig) {
        this.addToConfig = addToConfig;
    }

    public void setLazyInitialization(boolean lazyInitialization) {
        this.lazyInitialization = lazyInitialization;
    }

    public void setPrintWarnLogIfNotFoundRetrofits(boolean printWarnLogIfNotFoundRetrofits) {
        this.printWarnLogIfNotFoundRetrofits = printWarnLogIfNotFoundRetrofits;
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public void setMarkerInterface(Class<?> markerInterface) {
        this.markerInterface = markerInterface;
    }

    public void setRetrofitFactoryBeanClass(Class<? extends RetrofitFactoryBean> retrofitFactoryBeanClass) {
        this.retrofitFactoryBeanClass = retrofitFactoryBeanClass == null ? RetrofitFactoryBean.class : retrofitFactoryBeanClass;
    }

    /**
     * Configures parent scanner to search for the right interfaces. It can search for all interfaces or just for those
     * that extends a markerInterface or/and those annotated with the annotationClass
     */
    public void registerFilters() {
        boolean acceptAllInterfaces = true;

        // if specified, use the given annotation and / or marker interface
        if (this.annotationClass != null) {
            addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
            acceptAllInterfaces = false;
        }

        // override AssignableTypeFilter to ignore matches on the actual marker interface
        if (this.markerInterface != null) {
            addIncludeFilter(new AssignableTypeFilter(this.markerInterface) {
                @Override
                protected boolean matchClassName(String className) {
                    return false;
                }
            });
            acceptAllInterfaces = false;
        }

        if (acceptAllInterfaces) {
            // default include filter that accepts all classes
            addIncludeFilter((metadataReader, metadataReaderFactory) -> true);
        }

        // exclude package-info.java
        addExcludeFilter((metadataReader, metadataReaderFactory) -> {
            String className = metadataReader.getClassMetadata().getClassName();
            return className.endsWith("package-info");
        });
    }


    /**
     * Calls the parent search that will search and register all the candidates. Then the registered objects are post
     * processed to set them as MapperFactoryBeans
     */
    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

        if (beanDefinitions.isEmpty()) {
            if (printWarnLogIfNotFoundRetrofits) {
                log.warn("No MyBatis mapper was found in '" + Arrays.toString(basePackages)
                        + "' package. Please check your configuration.");
            }
        } else {
            processBeanDefinitions(beanDefinitions);
        }

        return beanDefinitions;
    }


    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        AbstractBeanDefinition definition;
        BeanDefinitionRegistry registry = getRegistry();
        for (BeanDefinitionHolder holder : beanDefinitions) {
            definition = (AbstractBeanDefinition) holder.getBeanDefinition();
            boolean scopedProxy = false;
            if (ScopedProxyFactoryBean.class.getName().equals(definition.getBeanClassName())) {
                definition = (AbstractBeanDefinition) Optional
                        .ofNullable(((RootBeanDefinition) definition).getDecoratedDefinition())
                        .map(BeanDefinitionHolder::getBeanDefinition).orElseThrow(() -> new IllegalStateException(
                                "The target bean definition of scoped proxy bean not found. Root bean definition[" + holder + "]"));
                scopedProxy = true;
            }
            String beanClassName = definition.getBeanClassName();
            log.debug("Creating MapperFactoryBean with name '" + holder.getBeanName() + "' and '" + beanClassName
                    + "' mapperInterface");

            // the mapper interface is the original class of the bean
            // but, the actual class of the bean is MapperFactoryBean
            definition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName); // issue #59
            try {
                Class<?> beanClass = Resources.classForName(beanClassName);
                // Attribute for MockitoPostProcessor
                // https://github.com/mybatis/spring-boot-starter/issues/475
                definition.setAttribute(FACTORY_BEAN_OBJECT_TYPE, beanClass);
                // for spring-native
                definition.getPropertyValues().add("retrofitInterface", beanClass);
            } catch (ClassNotFoundException ignore) {
                // ignore
            }

            definition.setBeanClass(this.retrofitFactoryBeanClass);

            definition.getPropertyValues().add("addToConfig", this.addToConfig);

            log.debug("Enabling autowire by type for MapperFactoryBean with name '" + holder.getBeanName() + "'.");
            definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

            definition.setLazyInit(lazyInitialization);

            if (scopedProxy) {
                continue;
            }

            if (!definition.isSingleton()) {
                BeanDefinitionHolder proxyHolder = ScopedProxyUtils.createScopedProxy(holder, registry, true);
                if (registry.containsBeanDefinition(proxyHolder.getBeanName())) {
                    registry.removeBeanDefinition(proxyHolder.getBeanName());
                }
                registry.registerBeanDefinition(proxyHolder.getBeanName(), proxyHolder.getBeanDefinition());
            }
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }
}
