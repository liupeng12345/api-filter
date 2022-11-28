package com.pzhu.filter.filter;

import com.pzhu.filter.exception.DetailedIllegalArgumentException;
import com.pzhu.filter.g4.FilterBaseVisitor;
import com.pzhu.filter.g4.FilterParser;
import com.pzhu.filter.metadata.FilterBeanField;
import com.pzhu.filter.metadata.FilterBeanInfo;
import com.pzhu.filter.query.FilterBeanSupport;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class ValueBaseFilter extends FilterBaseVisitor<Object> implements FilterBeanSupport {

    @Setter
    @Getter
    private FilterBeanInfo filterBeanInfo;

    @Setter
    @Getter
    private String fieldName;

    @Override
    public Object visitNumber(FilterParser.NumberContext ctx) {
        final FilterBeanField filterBeanField =
                filterBeanInfo.getSearchBeanFieldMap().get(fieldName);
        final String text = ctx.NUMBER().getText();
        return filterBeanField.getConvert().convert(text);
    }

    @Override
    public Object visitString(FilterParser.StringContext ctx) {
        String text = ctx.getChild(0).getText();
        final FilterBeanField filterBeanField =
                filterBeanInfo.getSearchBeanFieldMap().get(fieldName);
        if (text.contains(",")) {
            return Arrays.stream(text.split(","))
                    .map(value -> filterBeanField.getConvert().convert(value))
                    .collect(Collectors.toList());
        }
        text = org.apache.commons.lang3.StringUtils.strip(text, "'");
        text = org.apache.commons.lang3.StringUtils.strip(text, "\"");
        return filterBeanField.getConvert().convert(text);
    }

    @Override
    public String visitField(FilterParser.FieldContext ctx) {
        fieldName = ctx.getRuleContext().getText();
        final FilterBeanField filterBeanField =
                filterBeanInfo.getSearchBeanFieldMap().get(fieldName);
        if (filterBeanField == null) {
            throw new DetailedIllegalArgumentException(String.format("%s field not found", fieldName));
        }
        if (!filterBeanField.isCanSearch()) {
            throw new DetailedIllegalArgumentException(String.format("%s field cannot be sorted", fieldName));
        }
        return fieldName;
    }
}
