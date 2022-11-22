package com.pzhu.filter.filter;

import com.pzhu.filter.g4.FilterParser;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public abstract class SqlFilterVisitor extends ValueBaseFilter {
    /**
     * 参数列表
     */
    @Getter
    protected final Map<String, Object> paramNameValuePairs = new HashMap<>();

    /**
     * 参数位置
     */
    protected Integer index = 0;

    abstract String conditionHandler(String field, String comparator, Object value);

    abstract String inConditionHandler(String field, Object value);

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
            final String comparator = ctx.isComparator().getText();
            checkField(field, comparator);
            return String.format(" %s %s ", getDbField(field), comparator);
        } else {
            String comparator = visitComparator(ctx.comparator());
            Object value = visitValue(ctx.value());
            checkField(field, comparator);
            // 处理包含查询
            if (":".equals(comparator)) {
                return inConditionHandler(field, value);
            } else {
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
                return conditionHandler(getDbField(field), comparator, value);
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
}
