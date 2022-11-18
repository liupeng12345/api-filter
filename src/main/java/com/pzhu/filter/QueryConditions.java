package com.pzhu.filter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.filter.filter.FilterVisitor;
import com.pzhu.filter.g4.FilterLexer;
import com.pzhu.filter.g4.FilterParser;
import com.pzhu.filter.metadata.SearchBeanField;
import com.pzhu.filter.metadata.SearchBeanInfo;
import com.pzhu.filter.metadata.SearchBeanInfoHelper;
import com.pzhu.filter.wrapper.SqlWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.apache.commons.lang3.StringUtils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class QueryConditions {

    public static final String FILTER = "filter";

    public static final String ORDER_BY = "orderBy";

    public static final String SEARCH_LIST = "searchList";

    public static final String PAGE_SIZE = "pageSize";

    public static final String PAGE = "page";

    private String filter;
    private String orderBy;
    private String searchList;
    private Integer pageSize;
    private Integer page;
    private Class<?> searchBeanClass;

    private String sqlPrefix;

    public SqlWrapper createSqlWrapper(Class<?> searchBeanClass) {
        this.searchBeanClass = searchBeanClass;
        final SqlWrapper SqlWrapper = new SqlWrapper();
        final SearchBeanInfo searchBeanInfo = SearchBeanInfoHelper.getInfo(searchBeanClass);
        loadSearchList(searchBeanInfo, SqlWrapper);
        loadFilter(searchBeanInfo, SqlWrapper);
        loadOrderBy(searchBeanInfo, SqlWrapper);
        return SqlWrapper;
    }

    private void loadOrderBy(SearchBeanInfo searchBeanInfo, SqlWrapper SqlWrapper) {
        if (StringUtils.isBlank(orderBy)) {
            return;
        }
        orderBy = URLDecoder.decode(orderBy, StandardCharsets.UTF_8);
        final String orderBySql = Arrays.stream(orderBy.split(","))
                .map(orderByStr -> toOrderByCondition(searchBeanInfo, orderByStr))
                .filter(Objects::nonNull)
                .map(OrderByCondition::toString)
                .collect(Collectors.joining(","));
        SqlWrapper.setOrderBy(orderBySql);
    }

    private void loadSearchList(SearchBeanInfo searchBeanInfo, SqlWrapper SqlWrapper) {
        final Map<String, SearchBeanField> searchBeanFieldMap = searchBeanInfo.getSearchBeanFieldMap();
        Stream<SearchBeanField> stream;
        if (StringUtils.isNotBlank(searchList)) {
            stream = Arrays.stream(searchList.split(","))
                    .map(String::trim)
                    .filter(searchBeanFieldMap.keySet()::contains)
                    .map(searchBeanFieldMap::get);
        } else {
            stream = searchBeanFieldMap.values().stream();
        }
        SqlWrapper.setSearchList(stream.map(this::column).collect(Collectors.joining(",")));
    }

    private void loadFilter(SearchBeanInfo searchBeanInfo, SqlWrapper queryWrapper) {
        if (StringUtils.isBlank(filter)) {
            return;
        }
        filter = URLDecoder.decode(filter, StandardCharsets.UTF_8);
        Lexer lexer = new FilterLexer(CharStreams.fromString(filter));
        TokenStream tokenStream = new CommonTokenStream(lexer);
        FilterParser parser = new FilterParser(tokenStream);
        final FilterVisitor visitor = new FilterVisitor(searchBeanInfo);
        final FilterParser.FilterContext tree = parser.filter();
        final String where = visitor.visit(tree).toString();
        queryWrapper.setWhereSql(where);
        queryWrapper.getParamNameValuePairs().putAll(visitor.getParamNameValuePairs());
    }

    /**
     * 封装
     *
     * @return
     */
    private OrderByCondition toOrderByCondition(SearchBeanInfo searchBeanInfo, String orderBy) {
        final Map<String, SearchBeanField> searchBeanFieldMap = searchBeanInfo.getSearchBeanFieldMap();
        final String[] split = orderBy.split(" ");
        final SearchBeanField searchBeanField = searchBeanFieldMap.get(split[0]);
        if (searchBeanField != null) {
            if (split.length == 1) {
                return new OrderByCondition(searchBeanField.getDbField(), OrderType.ASC);
            } else if (split.length == 2) {
                final OrderType type = OrderType.getType(split[1].trim());
                if (type != null) {
                    return new OrderByCondition(searchBeanField.getDbField(), type);
                }
                return new OrderByCondition(searchBeanField.getDbField(), null);
            }
        }
        return null;
    }

    public String column(SearchBeanField field) {
        return String.format(
                " %s %s ",
                field.getDbField(),
                com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(field.getFieldName()));
    }

    public Page page() {
        return Page.of(page == null ? 1 : page, pageSize == null ? 10 : pageSize);
    }

    private enum OrderType {
        ASC,
        DESC;

        public static OrderType getType(String type) {
            if ("asc".equalsIgnoreCase(type)) {
                return ASC;
            }
            if ("desc".equalsIgnoreCase(type)) {
                return DESC;
            }
            return null;
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