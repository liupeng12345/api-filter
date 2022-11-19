package com.pzhu.filter.utils;

import com.pzhu.filter.metadata.SearchBeanInfo;
import com.pzhu.filter.wrapper.QueryWrapper;
import com.pzhu.filter.wrapper.SqlWrapper;
import lombok.AllArgsConstructor;

import java.util.Optional;

public abstract class QueryConditions<T extends QueryWrapper> {

    public static final String FILTER = "filter";
    public static final String ORDER_BY = "orderBy";
    public static final String PAGE_SIZE = "pageSize";
    public static final String PAGE = "page";

    abstract Object page();

    public abstract T createSqlWrapper(Class<?> searchBeanClass);

    protected abstract void loadOrderBy(SearchBeanInfo searchBeanInfo, SqlWrapper SqlWrapper);

    protected abstract void loadFilter(SearchBeanInfo searchBeanInfo, SqlWrapper queryWrapper);

    private enum OrderType {
        ASC, DESC;

        public static OrderType form(Object type) {
            Optional.ofNullable(type).orElseThrow();
            if (type instanceof OrderType) {
                return (OrderType) type;
            }
            return switch (type.toString().toLowerCase()) {
                case "ase" -> ASC;
                case "desc" -> DESC;
                default -> null;
            };
        }
    }

    @AllArgsConstructor
    private static class OrderByCondition {
        private String field;
        private OrderType orderType;

        @Override
        public String toString() {
            return String.format(" %s %s ", field, orderType.name());
        }
    }
}