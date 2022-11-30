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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class FilterHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final List<QueryConditionsFactory<? extends QueryConditions<? extends QueryWrapper>>>
            queryConditionsFactoryList;

    // 参数是 SearchWrapper .并且有注解 @RequestFilter
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return (methodParameter.getParameterType().getSuperclass().equals(QueryWrapper.class)
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
        Class<?> type = methodParameter.getParameterType();
        String[] filterParameterValues = nativeWebRequest.getParameterValues(QueryConditions.FILTER);
        String filterStr = Optional.ofNullable(filterParameterValues)
                .flatMap(parameterValue -> Arrays.stream(parameterValue).findFirst())
                .orElse("");
        String orderStr = Optional.ofNullable(nativeWebRequest.getParameterValues(QueryConditions.ORDER))
                .flatMap(values -> Arrays.stream(values).findFirst())
                .orElse("");
        Integer page = Optional.ofNullable(nativeWebRequest.getParameterValues(QueryConditions.PAGE))
                .flatMap(values -> Arrays.stream(values).findFirst())
                .map(Integer::parseInt)
                .orElse(1);
        Integer pageSize = Optional.ofNullable(nativeWebRequest.getParameterValues(QueryConditions.PAGE_SIZE))
                .flatMap(values -> Arrays.stream(values).findFirst())
                .map(Integer::parseInt)
                .orElse(10);
        return queryConditionsFactoryList.stream()
                .filter(queryConditionsFactory -> queryConditionsFactory.check(type))
                .findAny()
                .map(queryConditionsFactory ->
                        queryConditionsFactory.getQueryConditions(page, pageSize, filterStr, orderStr))
                .map(queryConditions -> queryConditions.wrapper(filterClass))
                .orElseThrow();
    }
}
