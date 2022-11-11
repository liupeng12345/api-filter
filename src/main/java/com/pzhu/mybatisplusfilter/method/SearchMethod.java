package com.pzhu.mybatisplusfilter.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.pzhu.mybatisplusfilter.metadata.SearchBeanInfo;
import lombok.AllArgsConstructor;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.Optional;

@AllArgsConstructor
public class SearchMethod extends AbstractMethod {

    private static final String SEARCH_SQL = "<script> select %s FROM %s %s %s \n </script>";
    private static final String SEARCH_COUNT_SQL = "<script> select count(*) FROM %s %s %s \n </script>";
    private static final String CONDITION = "${ew.getSqlSegment()}";
    private static final String WHERE = "${ew.getWhereSql()}";
    private static final String SEARCH_LIST = "${ew.getSearchList()}";
    private String methodName;

    /**
     * 自定义注解
     */
    public void inject(
            MapperBuilderAssistant builderAssistant,
            Class<?> mapperClass,
            Boolean isCount,
            Class<?> modelClass,
            SearchBeanInfo searchBeanInfo,
            Class<?> methodReturnType) {
        this.configuration = builderAssistant.getConfiguration();
        this.builderAssistant = builderAssistant;
        this.languageDriver = configuration.getDefaultScriptingLanguageInstance();
        injectMappedStatement(mapperClass, modelClass, isCount, searchBeanInfo, methodReturnType);
    }

    public void injectMappedStatement(
            Class<?> mapperClass,
            Class<?> modelClass,
            Boolean isCount,
            SearchBeanInfo searchBeanInfo,
            Class<?> methodReturnType) {
        String sql;
        if (Boolean.TRUE.equals(isCount)) {
            sql = String.format(SEARCH_COUNT_SQL, searchBeanInfo.getTables(), WHERE, sqlComment());
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
            this.addSelectMappedStatementForOther(mapperClass, methodName, sqlSource, Long.class);
        } else {
            sql = String.format(
                    SEARCH_SQL,
                    Optional.ofNullable(searchBeanInfo.getSelect()).orElse(SEARCH_LIST),
                    searchBeanInfo.getTables(),
                    CONDITION,
                    sqlComment());
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, methodReturnType);
            this.addSelectMappedStatementForOther(mapperClass, methodName, sqlSource, modelClass);
        }
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        return null;
    }
}
