package com.pzhu.mybatisplusfilter.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.pzhu.mybatisplusfilter.exception.DetailedIllegalArgumentException;
import com.pzhu.mybatisplusfilter.g4.FilterBaseVisitor;
import com.pzhu.mybatisplusfilter.g4.FilterParser;
import com.pzhu.mybatisplusfilter.metadata.SearchBeanField;
import com.pzhu.mybatisplusfilter.metadata.SearchBeanInfo;
import com.pzhu.mybatisplusfilter.query.SearchBeanSupport;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@EqualsAndHashCode(callSuper = false)
@Slf4j
public class FilterVisitor extends FilterBaseVisitor<Object> implements SearchBeanSupport {
    /**
     * 参数前缀
     */
    private static final String PARAM = "Param";

    /**
     * 条件语句处理
     */
    private static final String CONDITION_STENCIL = " %s %s #{ew.paramNameValuePairs.Param%s} ";

    private static final String IN_CONDITION_PREFIX = " %s in ";

    /**
     * 参数列表
     */
    private final Map<String, Object> paramNameValuePairs = new HashMap<>();

    private boolean isString;

    private SearchBeanInfo searchBeanInfo;

    private String fieldName;

    /**
     * 参数位置
     */
    private Integer index = 0;

    public FilterVisitor(SearchBeanInfo searchBeanInfo) {
        this.searchBeanInfo = searchBeanInfo;
    }

    @Override
    protected Object aggregateResult(Object aggregate, Object nextResult) {
        if (aggregate == null) {
            return nextResult;
        }
        if (nextResult == null) {
            return aggregate;
        }
        return aggregate.toString().trim() + " " + nextResult.toString().trim();
    }

    @Override
    public Object visitNumber(FilterParser.NumberContext ctx) {
        final SearchBeanField searchBeanField =
                searchBeanInfo.getSearchBeanFieldMap().get(fieldName);
        final String text = ctx.NUMBER().getText();
        return searchBeanField.getConvert().convert(text);
    }

    @Override
    public Object visitString(FilterParser.StringContext ctx) {
        String text = ctx.getChild(0).getText();
        final SearchBeanField searchBeanField =
                searchBeanInfo.getSearchBeanFieldMap().get(fieldName);
        if (text.contains(",")) {
            return Arrays.stream(text.split(","))
                    .map(value -> searchBeanField.getConvert().convert(value))
                    .collect(Collectors.toList());
        }
        text = org.apache.commons.lang3.StringUtils.strip(text, "'");
        text = org.apache.commons.lang3.StringUtils.strip(text, "\"");
        return searchBeanField.getConvert().convert(text);
    }

    @Override
    public Object visitField(FilterParser.FieldContext ctx) {
        fieldName = ctx.getRuleContext().getText();
        final SearchBeanField searchBeanField =
                searchBeanInfo.getSearchBeanFieldMap().get(fieldName);
        if (searchBeanField == null) {
            throw new DetailedIllegalArgumentException(String.format("%s field not found", fieldName));
        }
        if (!searchBeanField.isCanSearch()) {
            throw new DetailedIllegalArgumentException(String.format("%s field cannot be sorted", fieldName));
        }
        return fieldName;
    }

    @Override
    public Object visitConnection(FilterParser.ConnectionContext ctx) {
        return ctx.getRuleContext().getText();
    }

    @Override
    public Object visitRestriction(FilterParser.RestrictionContext ctx) {
        // 判断ctx
        final int childCount = ctx.getChildCount();
        if (childCount == 2) {
            final String field =
                    visitField((FilterParser.FieldContext) ctx.getChild(0)).toString();
            final String comparator = ctx.getChild(1).getText();
            checkField(field, comparator);
            return String.format(" %s %s ", getDbField(field), comparator);
        }
        if (childCount == 3) {
            final String field =
                    visitField((FilterParser.FieldContext) ctx.getChild(0)).toString();
            String comparator = visitComparator((FilterParser.ComparatorContext) ctx.getChild(1))
                    .toString();
            Object value = visitValue((FilterParser.ValueContext) ctx.getChild(2));
            checkField(field, comparator);
            // 处理包含查询
            if (":".equals(comparator)) {
                return getStringBuilder(field, value);
            } else {
                String key = PARAM + index;
                if ("like".equals(comparator)) {
                    comparator = "like";
                    value = "%" + value.toString() + "%";
                }
                if ("$ew".equals(comparator)) {
                    comparator = "like";
                    value = "%" + value.toString();
                }
                if ("$sw".equals(comparator)) {
                    comparator = "like";
                    value = value.toString() + "%";
                }
                paramNameValuePairs.put(key, value);
                return String.format(CONDITION_STENCIL, getDbField(field), comparator, index++);
            }
        }
        return super.visitRestriction(ctx);
    }

    private StringBuilder getStringBuilder(String field, Object value) {
        Stream<?> stream = value instanceof Collection ? ((Collection<?>) value).stream() : Stream.of(value);
        StringBuilder stringBuilder = new StringBuilder(String.format(IN_CONDITION_PREFIX, getDbField(field)));
        stringBuilder.append("( ");
        stringBuilder.append(stream.map(object -> {
                    String key = PARAM + index;
                    paramNameValuePairs.put(key, object);
                    return String.format("#{ew.paramNameValuePairs.Param%s}", index++);
                })
                .collect(Collectors.joining(",")));
        stringBuilder.append(" )");
        return stringBuilder;
    }

    @Override
    public Object visitComparator(FilterParser.ComparatorContext ctx) {
        return ctx.getRuleContext().getText();
    }

    @Override
    public Object visitComposite(FilterParser.CompositeContext ctx) {
        final int childCount = ctx.getChildCount();
        if (childCount == 3) {
            return String.format(" ( %s ) ", visitExpression((FilterParser.ExpressionContext) ctx.getChild(1)));
        }
        return "";
    }

    @Override
    public Object visitTerm(FilterParser.TermContext ctx) {
        final int childCount = ctx.getChildCount();
        if (childCount == 2) {
            final ParseTree child = ctx.getChild(1);
            return String.format(" %s = false", visitSimple((FilterParser.SimpleContext) child));
        }
        return super.visitTerm(ctx);
    }

    @Override
    public Object visitValue(FilterParser.ValueContext ctx) {
        final Object value = super.visitValue(ctx);
        if (StringUtils.isBlank(value.toString())) {
            throw new DetailedIllegalArgumentException(String.format("%s is error value", value));
        }
        return value;
    }
}
