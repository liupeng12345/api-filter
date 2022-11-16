package com.pzhu.mybatisplusfilter.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mongodb.client.model.Filters;
import com.pzhu.mybatisplusfilter.enums.Connection;
import com.pzhu.mybatisplusfilter.enums.Operator;
import com.pzhu.mybatisplusfilter.exception.DetailedIllegalArgumentException;
import com.pzhu.mybatisplusfilter.g4.FilterBaseVisitor;
import com.pzhu.mybatisplusfilter.g4.FilterParser;
import com.pzhu.mybatisplusfilter.metadata.SearchBeanField;
import com.pzhu.mybatisplusfilter.metadata.SearchBeanInfo;
import com.pzhu.mybatisplusfilter.query.SearchBeanSupport;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
@Slf4j
public class MongoFilter extends FilterBaseVisitor<Object> implements SearchBeanSupport {

    private SearchBeanInfo searchBeanInfo;

    private String fieldName;

    public MongoFilter(SearchBeanInfo searchBeanInfo) {
        this.searchBeanInfo = searchBeanInfo;
    }

    @Override
    public Object visitValue(FilterParser.ValueContext ctx) {
        final Object value = super.visitValue(ctx);
        if (StringUtils.isBlank(value.toString())) {
            throw new DetailedIllegalArgumentException(String.format("%s is error value", value));
        }
        return value;
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
    public String visitField(FilterParser.FieldContext ctx) {
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
    public Connection visitConnection(FilterParser.ConnectionContext ctx) {
        return Connection.from(ctx.getRuleContext().getText());
    }

    @Override
    public Bson visitRestriction(FilterParser.RestrictionContext ctx) {
        int childCount = ctx.getChildCount();
        if (childCount == 2) {
            FilterParser.IsComparatorContext isComparatorContext = ctx.isComparator();
            FilterParser.FieldContext fieldContext = ctx.field();
            Object comparator = visitIsComparator(isComparatorContext);
            String field = visitField(fieldContext);
            Operator operator = Operator.from(comparator);
            checkField(field, comparator.toString());
            if (Operator.NOT_NULL.equals(operator)) {
                return Filters.exists(field);
            } else {
                return Filters.exists(field, false);
            }
        } else {
            FilterParser.FieldContext fieldContext = ctx.field();
            FilterParser.ComparatorContext comparatorContext = ctx.comparator();
            FilterParser.ValueContext valueContext = ctx.value();
            String field = visitField(fieldContext);
            String comparator = visitComparator(comparatorContext);
            checkField(field, comparator);
            Operator operator = Operator.from(comparator);
            Object value = visitValue(valueContext);
            return switch (operator) {
                case EQUAL -> Filters.eq(fieldName, value);
                case GREATER_EQUAL -> Filters.gte(fieldName, value);
                case GREATER_THAN -> Filters.gt(fieldName, value);
                case LESS_EQUAL -> Filters.lte(fieldName, value);
                case LESS_THAN -> Filters.lt(fieldName, value);
                case NOT_EQUAL -> Filters.ne(fieldName, value);
                case CONTAIN -> Filters.regex(fieldName, value.toString());
                case START_WITH -> Filters.regex(fieldName, String.format("^%s", value.toString()));
                case END_WITH -> Filters.regex(fieldName, String.format("%s$", value.toString()));
                case MULTI_VALUE -> Filters.in(fieldName, value);
                default -> throw new UnsupportedOperationException("operator value: " + operator);
            };
        }
    }

    @Override
    public Bson visitTerm(FilterParser.TermContext ctx) {
        FilterParser.RestrictionContext restrictionContext = ctx.restriction();
        FilterParser.CompositeContext compositeContext = ctx.composite();
        final int childCount = ctx.getChildCount();
        if (childCount == 2) {
            return Filters.not(visitRestriction(restrictionContext));
        }
        if (Objects.nonNull(restrictionContext)) {
            return visitRestriction(restrictionContext);
        } else {
            return visitComposite(compositeContext);
        }
    }

    @Override
    public Bson visitComposite(FilterParser.CompositeContext ctx) {
        FilterParser.ExpressionContext expression = ctx.expression();
        Object visitExpression = visitExpression(expression);
        Bson bson = (Bson) visitExpression;
        return Filters.and(bson);
    }

    @Override
    public Bson visitFactor(FilterParser.FactorContext ctx) {
        FilterParser.TermContext termContext = ctx.term();
        if (ctx.getChildCount() == 1) {
            return visitTerm(termContext);
        } else {
            FilterParser.ConnectionContext connectionContext = ctx.connection();
            FilterParser.FactorContext factorContext = ctx.factor();
            Connection connection = visitConnection(connectionContext);
            if (Connection.and.equals(connection)) {
                return Filters.and(visitTerm(termContext), visitFactor(factorContext));
            } else {
                return Filters.or(visitTerm(termContext), visitFactor(factorContext));
            }
        }
    }

    @Override
    public String visitComparator(FilterParser.ComparatorContext ctx) {
        return ctx.getText();
    }
}
