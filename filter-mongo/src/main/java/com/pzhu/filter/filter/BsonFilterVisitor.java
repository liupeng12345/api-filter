package com.pzhu.filter.filter;

import com.mongodb.client.model.Filters;
import com.pzhu.filter.enums.Connection;
import com.pzhu.filter.enums.Operator;
import com.pzhu.filter.g4.FilterParser;
import com.pzhu.filter.metadata.FilterBeanInfo;
import org.bson.conversions.Bson;

import java.util.Objects;

public class BsonFilterVisitor extends ValueBaseFilter {

    public BsonFilterVisitor(FilterBeanInfo filterBeanInfo) {
        setFilterBeanInfo(filterBeanInfo);
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
            String comparator = isComparatorContext.getText();
            String field = visitField(fieldContext);
            Operator operator = Operator.from(comparator);
            checkField(field, comparator);
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
            String fieldName = getFieldName();
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
