package com.pzhu.filter.metadata;

import com.pzhu.filter.annotation.CanOrderBy;
import com.pzhu.filter.annotation.Field;
import com.pzhu.filter.annotation.FilterBean;
import com.pzhu.filter.enums.Operator;
import com.pzhu.filter.function.Convert;
import com.pzhu.filter.utils.ConvertUtils;
import com.pzhu.filter.utils.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class FilterBeanInfoHelper {

    /**
     * 储存反射类表信息
     */
    private static final Map<Class<?>, FilterBeanInfo> TABLE_INFO_CACHE = new ConcurrentHashMap<>();

    private static synchronized FilterBeanInfo initTableInfo(Class<?> searchBeanClass) {
        if (Objects.nonNull(TABLE_INFO_CACHE.get(searchBeanClass))) {
            return TABLE_INFO_CACHE.get(searchBeanClass);
        }
        final FilterBeanInfo filterBeanInfo = new FilterBeanInfo();
        // 设置表类名
        final String tableName = StringUtils.camelToUnderline(searchBeanClass.getName());
        // 默认值
        filterBeanInfo.setTables(tableName);
        filterBeanInfo.setAutoMapTo(tableName);
        // 存在注解
        final FilterBean filterBean = searchBeanClass.getAnnotation(FilterBean.class);
        if (Objects.nonNull(filterBean)) {
            // 解析注解中的内容，并添加到filterBeanInfo中
            doProcessSearchBean(filterBean, filterBeanInfo);
        }
        // 解析字段 获取字段段上别名等信息
        final java.lang.reflect.Field[] searchBeanClassDeclaredFields = searchBeanClass.getDeclaredFields();
        final List<FilterBeanField> filterBeanFields = Arrays.stream(searchBeanClassDeclaredFields)
                .map(getMapper(filterBeanInfo))
                .filter(Objects::nonNull)
                .toList();
        final HashMap<String, FilterBeanField> searchBeanFieldHashMap = new HashMap<>();
        filterBeanFields.forEach(
                searchBeanField -> searchBeanFieldHashMap.put(searchBeanField.getFieldName(), searchBeanField));
        filterBeanInfo.setSearchBeanFieldMap(searchBeanFieldHashMap);
        TABLE_INFO_CACHE.put(searchBeanClass, filterBeanInfo);
        return filterBeanInfo;
    }

    private static Function<java.lang.reflect.Field, FilterBeanField> getMapper(FilterBeanInfo filterBeanInfo) {
        return field -> {
            try {
                return processSearchBeanField(field, filterBeanInfo.getAutoMapTo());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        };
    }

    private static void doProcessSearchBean(FilterBean filterBean, FilterBeanInfo filterBeanInfo) {
        // 设置表名
        final String tables = filterBean.tables();
        if (StringUtils.isNotBlank(tables)) {
            filterBeanInfo.setTables(tables);
        }
        // 设置自动表名
        final String autoMapTo = filterBean.autoMapTo();
        if (StringUtils.isNotBlank(autoMapTo)) {
            filterBeanInfo.setAutoMapTo(autoMapTo);
        }
        String select = filterBean.select();
        if (StringUtils.isNotBlank(select)) {
            filterBeanInfo.setSelect(select);
        }
    }

    private static FilterBeanField processSearchBeanField(java.lang.reflect.Field field, String autoName)
            throws InstantiationException, IllegalAccessException {
        final FilterBeanField filterBeanField = new FilterBeanField();
        // 设置字段名
        filterBeanField.setFieldName(field.getName());
        filterBeanField.setCanSort(false);
        filterBeanField.setClassType(field.getType());
        filterBeanField.setCanSearch(true);
        filterBeanField.setConvert(ConvertUtils.of(field.getType()));
        filterBeanField.setOnlyType(Arrays.asList(Operator.values()));
        // 设置数据库名
        String dbFieldName = StringUtils.camelToUnderline(field.getName());
        filterBeanField.setDbField(autoName + "." + dbFieldName);
        final Field dbField = field.getAnnotation(Field.class);
        if (dbField != null) {
            dbFieldName = dbField.value();
            if (StringUtils.isNotBlank(dbFieldName)) {
                filterBeanField.setDbField(dbFieldName);
            }
            final boolean conditional = dbField.conditional();
            filterBeanField.setCanSearch(conditional);
            final Operator[] operators = dbField.onlyOn();
            if (operators.length > 0) {
                filterBeanField.setOnlyType(Arrays.asList(operators));
            }
            Class<? extends Convert<?>> convertClass = dbField.convertClass();
            if (convertClass != null) {
                // 如果是枚举
                if (convertClass.isEnum()) {
                    Arrays.stream(convertClass.getEnumConstants()).findFirst().ifPresent(filterBeanField::setConvert);
                } else {
                    final Convert<?> convert;
                    try {
                        convert = convertClass.getDeclaredConstructor().newInstance();
                    } catch (InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                    filterBeanField.setConvert(convert);
                }
            }
        }
        final CanOrderBy annotation = field.getAnnotation(CanOrderBy.class);
        if (annotation != null) {
            filterBeanField.setCanSort(true);
        }
        return filterBeanField;
    }

    public static FilterBeanInfo getInfo(Class<?> searchBeanClass) {
        return Optional.ofNullable(TABLE_INFO_CACHE.get(searchBeanClass))
                .orElseGet(() -> FilterBeanInfoHelper.initTableInfo(searchBeanClass));
    }
}
