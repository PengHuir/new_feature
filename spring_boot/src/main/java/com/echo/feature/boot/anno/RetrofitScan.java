package com.echo.feature.boot.anno;

import com.echo.feature.boot.retrofit.RetrofitFactoryBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * Name: RetrofitScan
 * Description: Retrofit扫描配置
 * Author: zhangph
 * Date: 2024/1/11 14:57
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RetrofitScannerRegistrar.class)
@Repeatable(RetrofitScans.class)
public @interface RetrofitScan {
    /**
     * Alias for the {@link #basePackages()} attribute. Allows for more concise annotation declarations e.g.:
     * {@code @RetrofitScan("org.my.pkg")} instead of {@code @RetrofitScan(basePackages = "org.my.pkg"})}.
     *
     * @return base package names
     */
    @AliasFor("basePackages")
    String[] value() default {};

    /**
     * Base packages to scan for MyBatis interfaces. Note that only interfaces with at least one method will be
     * registered; concrete classes will be ignored.
     *
     * @return base package names for scanning Retrofit interface
     */
    @AliasFor("value")
    String[] basePackages() default {};

    /**
     * Type-safe alternative to {@link #basePackages()} for specifying the packages to scan for annotated components. The
     * package of each class specified will be scanned.
     * <p>
     * Consider creating a special no-op marker class or interface in each package that serves no purpose other than being
     * referenced by this attribute.
     *
     * @return classes that indicate base package for scanning Retrofit interface
     */
    Class<?>[] basePackageClasses() default {};

    /**
     * This property specifies the annotation that the scanner will search for.
     * <p>
     * The scanner will register all interfaces in the base package that also have the specified annotation.
     * <p>
     * Note this can be combined with markerInterface.
     *
     * @return the annotation that the scanner will search for
     */
    Class<? extends Annotation> annotationClass() default Annotation.class;

    /**
     * This property specifies the parent that the scanner will search for.
     * <p>
     * The scanner will register all interfaces in the base package that also have the specified interface class as a
     * parent.
     * <p>
     * Note this can be combined with annotationClass.
     *
     * @return the parent that the scanner will search for
     */
    Class<?> markerInterface() default Class.class;

    /**
     * Whether enable lazy initialization of mapper bean.
     * <p>
     * Default is {@code false}.
     * </p>
     *
     * @return set {@code true} to enable lazy initialization
     * @since 2.0.2
     */
    String lazyInitialization() default "";

    /**
     * Specifies a flag that whether execute a property placeholder processing or not.
     * <p>
     * The default is {@literal true}. This means that a property placeholder processing execute.
     *
     * @return a flag that whether execute a property placeholder processing or not
     * @since 3.0.3
     */
    boolean processPropertyPlaceHolders() default true;

    /**
     * Specifies a custom RetrofitFactoryBean to return a Retrofit proxy as spring bean.
     *
     * @return the class of {@code RetrofitFactoryBean}
     */
    Class<? extends RetrofitFactoryBean> factoryBean() default RetrofitFactoryBean.class;
}
