package com.pzhu.mybatisplusfilter.web;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.pzhu.mybatisplusfilter.QueryConditions;
import com.pzhu.mybatisplusfilter.annotation.RequestFilter;
import com.pzhu.mybatisplusfilter.query.SearchWrapper;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;
import java.util.Optional;

public class SqlFilterHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    // 参数是 SearchWrapper .并且有注解 @RequestFilter
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return (methodParameter.getParameterType().equals(SearchWrapper.class)
                && methodParameter.hasParameterAnnotation(RequestFilter.class));
    }

    @Override
    public Object resolveArgument(
            MethodParameter methodParameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest nativeWebRequest,
            WebDataBinderFactory binderFactory)
            throws Exception {
        RequestFilter filter = methodParameter.getParameterAnnotation(RequestFilter.class);
        Class<?> filterClass = filter.filterClass();
        String parameterName = Optional.ofNullable(filter.name())
                .filter(StringUtils::isNotBlank)
                .orElse(methodParameter.getParameterName());
        String[] parameterValues = nativeWebRequest.getParameterValues(parameterName);
        Optional<String> OptionalFilterStr = Optional.ofNullable(parameterValues)
                .flatMap(parameterValue -> Arrays.stream(parameterValue).findFirst());
        if (OptionalFilterStr.isPresent()) {
            final QueryConditions queryConditions = new QueryConditions();
            queryConditions.setFilter(OptionalFilterStr.get());
            return queryConditions.createSearchWrapper(filterClass);
        } else if (filter.required()) {
            throw new MissingServletRequestParameterException(parameterName, "string");
        }
        return null;
    }
}
