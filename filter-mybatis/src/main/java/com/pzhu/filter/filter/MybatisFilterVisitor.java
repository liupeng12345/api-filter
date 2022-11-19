package com.pzhu.filter.filter;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MybatisFilterVisitor extends SqlFilterVisitor {

    private static final String CONDITION_JOIN = " %s %s %s ";
    private static final String IN_CONDITION_PREFIX = " %s in  ";

    @Override
    String conditionHandler(String field, String comparator, Object value) {
        String key = PARAM + index++;
        paramNameValuePairs.put(key, value);
        return String.format(CONDITION_JOIN, field, comparator, key);
    }

    @Override
    String inConditionHandler(String field, Object value) {
        return join(field, value);
    }

    private String join(String field, Object value) {
        Stream<?> stream = value instanceof Collection ? ((Collection<?>) value).stream() : Stream.of(value);
        StringBuilder stringBuilder = new StringBuilder(String.format(IN_CONDITION_PREFIX, getDbField(field)));
        stringBuilder.append("( ");
        stringBuilder.append(stream.map(object -> {
            String key = PARAM + index++;
            paramNameValuePairs.put(key, object);
            return String.format("#{%s}", key);
        }).collect(Collectors.joining(",")));
        stringBuilder.append(" )");
        return stringBuilder.toString();
    }
}
