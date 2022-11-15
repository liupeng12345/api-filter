package com.pzhu.mybatisplusfilter.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mongodb.client.model.Filters;
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
    protected Object aggregateResult(Object aggregate, Object nextResult) {
        if (aggregate == null) {
            return nextResult;
        }
        if (nextResult == null) {
            return aggregate;
        }
        return Filters.and((Bson) aggregate, (Bson) nextResult);
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
        return null;
    }

    @Override
    public Bson visitComposite(FilterParser.CompositeContext ctx) {
        FilterParser.ExpressionContext expression = ctx.expression();
        Object visitExpression = visitExpression(expression);
        if (visitExpression instanceof Bson) {
            Bson bson = (Bson) visitExpression;
            return Filters.and(bson);
        }
    }
}
