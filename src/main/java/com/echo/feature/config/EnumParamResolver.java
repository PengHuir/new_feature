package com.echo.feature.config;

import cn.hutool.core.util.StrUtil;
import com.echo.feature.anno.EnumParamAnno;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/11/29 10:38
 */
public class EnumParamResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isEnum() && parameter.hasParameterAnnotation(EnumParamAnno.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        EnumParamAnno annotation = parameter.getParameterAnnotation(EnumParamAnno.class);
        String parameterName = annotation.parameterName();
        String valueMethod = annotation.valueMethod();

        // 如果枚举没有配置使用参数名称获取
        if (StrUtil.isBlank(parameterName)) {
            parameterName = parameter.getParameterName();
        }

        String paramValue = webRequest.getParameter(parameterName);
        if (Objects.isNull(paramValue)) {
            throw new IllegalArgumentException("Missing parameter: " + parameterName);
        }

        if (valueMethod.isEmpty()) {
            return Enum.valueOf((Class<Enum>) parameter.getParameterType(), paramValue);
        } else {
            try {
                // 通过反射调用指定的方法获取Enum值
                Method method = parameter.getParameterType().getMethod(valueMethod);
                Object[] enumConstants = parameter.getParameterType().getEnumConstants();
                for (Object enumConstant : enumConstants) {
                    Object value = method.invoke(enumConstant);
                    if (value.toString().equals(paramValue)) {
                        return enumConstant;
                    }
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalArgumentException("Invalid valueMethod: " + valueMethod, e);
            }
        }

        throw new IllegalArgumentException("Invalid parameter value: " + paramValue);
    }
}
