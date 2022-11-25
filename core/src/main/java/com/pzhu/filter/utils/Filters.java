package com.pzhu.filter.utils;

import com.pzhu.filter.enums.Operator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *  用于创建filter 条件
 */
@Data
@AllArgsConstructor
public class Filters {

    private static final String AND = " AND ";

    private static final String OR = " OR ";

    private static final String BRACKETS = " ( %s ) ";

    private static final String NUMBER = "%s";

    private static final String STR = "'%s'";

    private static final String SPLICE = " %s %s %s";

    private static final String IN = "%s:'%S'";

    private static final String NULL = "%s %s";

    @Getter
    private String value;

    private static Filters filtersBrackets(String collect) {
        if (StringUtils.isBlank(collect)) {
            return new Filters("");
        }
        return new Filters(String.format(BRACKETS, collect));
    }

    /**
     * 且条件
     */
    public static Filters and(Filters... filters) {
        String collect = Arrays.stream(filters)
                .map(Filters::getValue)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(AND));
        return filtersBrackets(collect);
    }

    /**
     * 或
     */
    public static Filters or(Filters... filters) {
        String collect = Arrays.stream(filters)
                .map(Filters::getValue)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(OR));
        return filtersBrackets(collect);
    }

    /**
     * 通过get方法获取字段名
     */
    public static <T> String getFiled(LambdaUtils.SFunction<T, ?> function) {
        return LambdaUtils.getFunctionName(function);
    }

    public static <T, R> Filters isNull(LambdaUtils.SFunction<R, ?> function) {
        String filed = getFiled(function);
        return new Filters(NULL.formatted(filed, Operator.NULL.getSymbol()));
    }

    public static <T, R> Filters eq(LambdaUtils.SFunction<R, ?> function, T value) {
        return operator(function, Operator.EQUAL, value);
    }

    public static <T, R> Filters ge(LambdaUtils.SFunction<R, ?> function, T value) {
        return operator(function, Operator.GREATER_EQUAL, value);
    }

    public static <T, R> Filters gt(LambdaUtils.SFunction<R, ?> function, T value) {
        return operator(function, Operator.GREATER_THAN, value);
    }

    public static <T, R> Filters lt(LambdaUtils.SFunction<R, ?> function, T value) {
        return operator(function, Operator.LESS_THAN, value);
    }

    public static <T, R> Filters ne(LambdaUtils.SFunction<R, ?> function, T value) {
        return operator(function, Operator.NOT_EQUAL, value);
    }

    public static <T, R> Filters like(LambdaUtils.SFunction<R, ?> function, T value) {
        return operator(function, Operator.CONTAIN, value);
    }

    public static <T, R> Filters sw(LambdaUtils.SFunction<R, ?> function, T value) {
        return operator(function, Operator.START_WITH, value);
    }

    public static <T, R> Filters ew(LambdaUtils.SFunction<R, ?> function, T value) {
        return operator(function, Operator.END_WITH, value);
    }

    public static <T, R> Filters in(LambdaUtils.SFunction<R, ?> function, Collection<T> value) {
        return operator(function, Operator.MULTI_VALUE, value);
    }

    public static <T, R> Filters notNull(LambdaUtils.SFunction<R, ?> function) {
        String filed = getFiled(function);
        return new Filters(NULL.formatted(filed, Operator.NOT_NULL.getSymbol()));
    }

    public static <T, R> Filters operator(LambdaUtils.SFunction<R, ?> function, Operator operator, T value) {
        return operator(function, operator, value, Objects::toString, NumberUtils.isCreatable(value.toString()));
    }

    public static <T, R> Filters operator(
            LambdaUtils.SFunction<R, ?> function, Operator operator, T value, Boolean isNumber) {
        return operator(function, operator, value, Objects::toString, isNumber);
    }

    public static <T, R> Filters operator(
            LambdaUtils.SFunction<R, ?> function,
            Operator operator,
            T value,
            Function<T, String> convert,
            Boolean isNumber) {

        String filed = getFiled(function);
        String valueStr;
        if (operator.equals(Operator.MULTI_VALUE) && value instanceof Collection<?>) {
            valueStr = ((Collection<T>) value).stream().map(convert).collect(Collectors.joining(","));
            return new Filters(IN.formatted(filed, valueStr));
        }
        valueStr = convert.apply(value);
        if (isNumber) {
            return new Filters(SPLICE.formatted(filed, operator.getSymbol(), valueStr));
        } else {
            return new Filters(SPLICE.formatted(filed, operator.getSymbol(), STR.formatted(valueStr)));
        }
    }
}
