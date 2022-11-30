package com.pzhu.filter.config;

import com.pzhu.filter.annotation.RequestFilter;
import com.pzhu.filter.utils.QueryConditions;
import com.pzhu.filter.wrapper.QueryWrapper;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class FilterHandlerMethodArgumentResolverFlux implements HandlerMethodArgumentResolver {

    private final List<QueryConditionsFactory<? extends QueryConditions<? extends QueryWrapper>>>
            queryConditionsFactoryList;

    // 参数是 SearchWrapper .并且有注解 @RequestFilter
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return (methodParameter.getParameterType().equals(QueryWrapper.class)
                && methodParameter.hasParameterAnnotation(RequestFilter.class));
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter methodParameter, BindingContext bindingContext, ServerWebExchange exchange) {
        RequestFilter filter = methodParameter.getParameterAnnotation(RequestFilter.class);
        Class<?> filterClass =
                Optional.ofNullable(filter).map(RequestFilter::filterClass).orElseThrow();
        Class<?> type = methodParameter.getParameterType();
        //todo
        return Mono.empty();
    }

}
