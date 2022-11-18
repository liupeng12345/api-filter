package com.pzhu.filter.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pzhu.filter.QueryConditions;
import com.pzhu.filter.annotation.RequestFilter;
import org.springframework.core.MethodParameter;
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
        return (methodParameter.getParameterType().equals(QueryWrapper.class)
                && methodParameter.hasParameterAnnotation(RequestFilter.class));
    }

    @Override
    public Object resolveArgument(
            MethodParameter methodParameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest nativeWebRequest,
            WebDataBinderFactory binderFactory) {
        RequestFilter filter = methodParameter.getParameterAnnotation(RequestFilter.class);
        Class<?> filterClass =
                Optional.ofNullable(filter).map(RequestFilter::filterClass).orElseThrow();
        final QueryConditions queryConditions = new QueryConditions();
        Optional.ofNullable(nativeWebRequest.getParameterValues(QueryConditions.FILTER))
                .flatMap(parameterValue -> Arrays.stream(parameterValue).findFirst())
                .ifPresent(queryConditions::setFilter);
        Optional.ofNullable(nativeWebRequest.getParameterValues(QueryConditions.ORDER_BY))
                .flatMap(parameterValues -> Arrays.stream(parameterValues).findFirst())
                .ifPresent(queryConditions::setOrderBy);
        Optional.ofNullable(nativeWebRequest.getParameterValues(QueryConditions.PAGE))
                .flatMap(parameterValues -> Arrays.stream(parameterValues).findFirst())
                .map(Integer::parseInt)
                .ifPresent(queryConditions::setPage);
        Optional.ofNullable(nativeWebRequest.getParameterValues(QueryConditions.PAGE_SIZE))
                .flatMap(parameterValues -> Arrays.stream(parameterValues).findFirst())
                .map(Integer::parseInt)
                .ifPresent(queryConditions::setPageSize);
        return queryConditions.createSqlWrapper(filterClass);
    }
}
