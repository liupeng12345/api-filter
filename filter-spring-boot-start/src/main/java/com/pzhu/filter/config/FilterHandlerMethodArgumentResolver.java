package com.pzhu.filter.config;

import com.pzhu.filter.annotation.RequestFilter;
import com.pzhu.filter.utils.QueryConditions;
import com.pzhu.filter.wrapper.QueryWrapper;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

@AllArgsConstructor
public class FilterHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final QueryConditions<?> queryConditions;

    // 参数是 SearchWrapper .并且有注解 @RequestFilter
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return (methodParameter.getParameterType().equals(QueryWrapper.class)
                && methodParameter.hasParameterAnnotation(RequestFilter.class));
    }

    @Override
    public Object resolveArgument(
            MethodParameter methodParameter,
            ModelAndViewContainer mavContainer,
            @NotNull NativeWebRequest nativeWebRequest,
            WebDataBinderFactory binderFactory) {
        RequestFilter filter = methodParameter.getParameterAnnotation(RequestFilter.class);
        Class<?> filterClass =
                Optional.ofNullable(filter).map(RequestFilter::filterClass).orElseThrow();
        return queryConditions.wrapper(filterClass);
    }
}
