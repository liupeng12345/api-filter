package com.pzhu.filter.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.pzhu.filter.exception.DetailedIllegalArgumentException;
import com.pzhu.filter.g4.FilterParser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = false)
@Slf4j
public abstract class SqlFilterVisitor extends ValueBaseFilter {
    /**
     * 参数前缀
     */
    private static final String PARAM = "Param";

    /**
     * 参数列表
     */
    private final Map<String, Object> paramNameValuePairs = new HashMap<>();

    /**
     * 参数位置
     */
    private Integer index = 0;

    abstract String conditionHandler(String field, String comparator, Integer index);

    abstract String inConditionHandler(String field, Object value, Integer index);

    @Override
    public String visitConnection(FilterParser.ConnectionContext ctx) {
        return ctx.getRuleContext().getText();
    }

    @Override
    public String visitRestriction(FilterParser.RestrictionContext ctx) {
        // 判断ctx
        final int childCount = ctx.getChildCount();
        final String field = visitField(ctx.field());
        if (childCount == 2) {
            final String comparator = visitComparator(ctx.comparator());
            checkField(field, comparator);
            return String.format(" %s %s ", getDbField(field), comparator);
        } else {
            String comparator = visitComparator(ctx.comparator());
            Object value = visitValue(ctx.value());
            checkField(field, comparator);
            // 处理包含查询
            if (":".equals(comparator)) {
                return inConditionHandler(field, value, index);
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
                return conditionHandler(getDbField(field), comparator, index++);
            }
        }
    }

    @Override
    public String visitComparator(FilterParser.ComparatorContext ctx) {
        return ctx.getRuleContext().getText();
    }

    @Override
    public String visitComposite(FilterParser.CompositeContext ctx) {
        return String.format(" ( %s ) ", visitExpression(ctx.expression()));
    }

    @Override
    public String visitTerm(FilterParser.TermContext ctx) {
        FilterParser.RestrictionContext restrictionContext = ctx.restriction();
        FilterParser.CompositeContext compositeContext = ctx.composite();
        final int childCount = ctx.getChildCount();
        if (childCount == 2) {
            return String.format(" %s = false ", visitRestriction(restrictionContext));
        }
        if (Objects.nonNull(restrictionContext)) {
            return visitRestriction(restrictionContext);
        } else {
            return visitComposite(compositeContext);
        }
    }

    @Override
    public Object visitFactor(FilterParser.FactorContext ctx) {
        FilterParser.TermContext termContext = ctx.term();
        if (ctx.getChildCount() == 1) {
            return visitTerm(termContext);
        } else {
            FilterParser.ConnectionContext connectionContext = ctx.connection();
            FilterParser.FactorContext factorContext = ctx.factor();
            String connection = visitConnection(connectionContext);
            return String.format(" %s %s %s ", visitTerm(termContext), connection, visitFactor(factorContext));
        }
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
