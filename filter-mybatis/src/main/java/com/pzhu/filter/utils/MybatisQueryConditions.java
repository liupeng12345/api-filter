package com.pzhu.filter.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.filter.filter.MybatisFilterVisitor;
import com.pzhu.filter.g4.FilterLexer;
import com.pzhu.filter.g4.FilterParser;
import com.pzhu.filter.wrapper.MybatisWrapper;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class MybatisQueryConditions extends QueryConditions<MybatisWrapper> {

    public MybatisQueryConditions(int page, int pageSize, String filter, String order) {
        super(page, pageSize, filter, order);
    }

    public MybatisQueryConditions(String filter, String order) {
        super(filter, order);
    }


    @Override
    protected void pageInfo(MybatisWrapper queryWrapper) {
        Page<?> pageInfo = Page.of(page, pageSize);
        queryWrapper.setPage(pageInfo);
    }

    protected void doLoadFilter(MybatisWrapper queryWrapper) {
        Lexer lexer = new FilterLexer(CharStreams.fromString(filter));
        TokenStream tokenStream = new CommonTokenStream(lexer);
        FilterParser parser = new FilterParser(tokenStream);
        MybatisFilterVisitor mybatisFilter = new MybatisFilterVisitor(searchBeanInfo);
        final FilterParser.FilterContext tree = parser.filter();
        String whereSql = (String) mybatisFilter.visit(tree);
        Map<String, Object> paramNameValuePairs = mybatisFilter.getParamNameValuePairs();
        queryWrapper.setWhereSql(whereSql);
        queryWrapper.setParamNameValuePairs(paramNameValuePairs);
    }

    protected void doLoadOrderBy(MybatisWrapper queryWrapper) {
        List<OrderByCondition> orderByConditions = orderByCondition();
        queryWrapper.setOrderBySql(
                orderByConditions.stream().map(Objects::toString).collect(Collectors.joining(",")));
    }
}
