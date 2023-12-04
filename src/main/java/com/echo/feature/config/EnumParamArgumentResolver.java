package com.echo.feature.config;

import cn.hutool.core.util.StrUtil;
import com.echo.feature.anno.EnumParam;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/11/29 10:38
 */
public class EnumParamArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(EnumParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        EnumParam annotation = parameter.getParameterAnnotation(EnumParam.class);

        if (Objects.nonNull(annotation)) {
            String parameterName = annotation.parameterName();

            // 如果枚举没有配置使用参数名称获取
            if (StrUtil.isBlank(parameterName)) {
                parameterName = parameter.getParameterName();
            }

            String value = webRequest.getParameter(parameterName);


        }


        return null;
    }
}
