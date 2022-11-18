package com.pzhu.filter.filter;

import com.pzhu.filter.exception.DetailedIllegalArgumentException;
import com.pzhu.filter.g4.FilterBaseVisitor;
import com.pzhu.filter.g4.FilterParser;
import com.pzhu.filter.metadata.SearchBeanField;
import com.pzhu.filter.metadata.SearchBeanInfo;
import com.pzhu.filter.query.SearchBeanSupport;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class ValueBaseFilter extends FilterBaseVisitor<Object> implements SearchBeanSupport {

    @Setter
    @Getter
    private SearchBeanInfo searchBeanInfo;

    @Setter
    @Getter
    private String fieldName;

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
}
