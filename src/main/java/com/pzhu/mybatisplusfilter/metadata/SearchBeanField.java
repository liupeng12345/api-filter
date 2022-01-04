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
    // 字段名
    private String fieldName;
    // 数据库字段名
    private String dbField;
    // 允许操作
    private List<Operator> onlyType;
    // 是否可以排序
    private boolean canSort;
    /**
     * 是否可以查询
     */
    private boolean canSearch;

    private Convert<?> convert;
}
