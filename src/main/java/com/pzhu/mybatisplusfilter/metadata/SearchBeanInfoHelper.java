package com.pzhu.mybatisplusfilter.metadata;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.pzhu.mybatisplusfilter.annotation.CanOrderBy;
import com.pzhu.mybatisplusfilter.annotation.DbField;
import com.pzhu.mybatisplusfilter.annotation.SearchBean;
import com.pzhu.mybatisplusfilter.enums.Operator;
import com.pzhu.mybatisplusfilter.filter.ConvertUtils;
import com.pzhu.mybatisplusfilter.function.Convert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;


public class SearchBeanInfoHelper {


    /**
     * 储存反射类表信息
     */
    private static final Map<Class<?>, SearchBeanInfo> TABLE_INFO_CACHE = new ConcurrentHashMap<>();


    public static synchronized SearchBeanInfo initTableInfo(Class<?> searchBeanClass) {
        final SearchBeanInfo searchBeanInfo = new SearchBeanInfo();
        // 设置表类名
        final String tableName = StringUtils.camelToUnderline(searchBeanClass.getName());
        // 默认值
        searchBeanInfo.setTables(tableName);
        searchBeanInfo.setAutoMapTo(tableName);
        // 存在注解
        final SearchBean searchBean = searchBeanClass.getAnnotation(SearchBean.class);
        if (Objects.nonNull(searchBean)) {
            //解析注解中的内容，并添加到searchBeanInfo中
            doProcessSearchBean(searchBean, searchBeanInfo);
        }
        // 解析字段 获取字段段上别名等信息
        final Field[] searchBeanClassDeclaredFields = searchBeanClass.getDeclaredFields();
        final List<SearchBeanField> searchBeanFields = Arrays.stream(searchBeanClassDeclaredFields).map(getMapper(searchBeanInfo)).filter(Objects::nonNull).collect(Collectors.toList());
        final HashMap<String, SearchBeanField> searchBeanFieldHashMap = new HashMap<>();
        searchBeanFields.forEach(searchBeanField -> searchBeanFieldHashMap.put(searchBeanField.getFieldName(), searchBeanField));
        searchBeanInfo.setSearchBeanFieldMap(searchBeanFieldHashMap);
        TABLE_INFO_CACHE.put(searchBeanClass, searchBeanInfo);
        return searchBeanInfo;
    }

    private static Function<Field, SearchBeanField> getMapper(SearchBeanInfo searchBeanInfo) {
        return field -> {
            try {
                return processSearchBeanField(field, searchBeanInfo.getAutoMapTo());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        };
    }

    private static void doProcessSearchBean(SearchBean searchBean, SearchBeanInfo searchBeanInfo) {
        // 设置表名
        final String tables = searchBean.tables();
        if (StringUtils.isNotBlank(tables)) {
            searchBeanInfo.setTables(tables);
        }
        // 设置自动表名
        final String autoMapTo = searchBean.autoMapTo();
        if (StringUtils.isNotBlank(autoMapTo)) {
            searchBeanInfo.setAutoMapTo(autoMapTo);
        }

    }

    private static SearchBeanField processSearchBeanField(Field field, String autoName) throws InstantiationException, IllegalAccessException {
        final SearchBeanField searchBeanField = new SearchBeanField();
        // 设置字段名
        searchBeanField.setFieldName(field.getName());
        searchBeanField.setCanSort(false);
        searchBeanField.setClassType(field.getType());
        searchBeanField.setCanSearch(true);
        searchBeanField.setConvert(ConvertUtils.of(field.getType()));
        searchBeanField.setOnlyType(Arrays.asList(Operator.values()));
        // 设置数据库名
        String dbFieldName = StringUtils.camelToUnderline(field.getName());
        searchBeanField.setDbField(autoName + StringPool.DOT + dbFieldName);
        final DbField dbField = field.getAnnotation(DbField.class);
        if (dbField != null) {
            dbFieldName = dbField.value();
            if (StringUtils.isNotBlank(dbFieldName)) {
                searchBeanField.setDbField(dbFieldName);
            }
            final boolean conditional = dbField.conditional();
            searchBeanField.setCanSearch(conditional);
            final Operator[] operators = dbField.onlyOn();
            if (operators.length > 0) {
                searchBeanField.setOnlyType(Arrays.asList(operators));
            }
            Class<? extends Convert<?>> convertClass = dbField.convertClass();
            if (convertClass != null) {
                // 如果是枚举
                if (convertClass.isEnum()) {
                    Arrays.stream(convertClass.getEnumConstants()).findFirst().ifPresent(searchBeanField::setConvert);
                } else {
                    final Convert<?> convert;
                    try {
                        convert = convertClass.getDeclaredConstructor().newInstance();
                    } catch (InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                    searchBeanField.setConvert(convert);
                }
            }
        }
        final CanOrderBy annotation = field.getAnnotation(CanOrderBy.class);
        if (annotation != null) {
            searchBeanField.setCanSort(true);
        }
        return searchBeanField;
    }


    public static SearchBeanInfo getInfo(Class<?> searchBeanClass) {
        return TABLE_INFO_CACHE.get(searchBeanClass);
    }


}
