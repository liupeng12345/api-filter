package com.pzhu.mybatisplusfilter.metadata;

import com.pzhu.mybatisplusfilter.enums.Operator;
import com.pzhu.mybatisplusfilter.function.Convert;
import lombok.Data;

import java.util.List;

@Data
public class SearchBeanField {

    /**
     * 字段类型
     */
    private Class<?> classType;
    /**
     * java字段名
     */
    private String fieldName;
    /**
     * 数据库字段名
     */
    private String dbField;
    /**
     * 允许的操作类型
     */
    private List<Operator> onlyType;
    /**
     * 是否可以排序
     */
    private boolean canSort;
    /**
     * 是否可以查询
     */
    private boolean canSearch;

    /**
     * 转化函数
     */
    private Convert<?> convert;
}
