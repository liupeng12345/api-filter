package com.pzhu.filter.query;

import com.pzhu.filter.enums.Operator;
import com.pzhu.filter.exception.DetailedIllegalArgumentException;
import com.pzhu.filter.metadata.FilterBeanField;
import com.pzhu.filter.metadata.FilterBeanInfo;
import org.apache.commons.lang3.StringUtils;

/**
 * 提供一些searchBean的扩展支持
 * @author 75073
 */
public interface FilterBeanSupport {

    /**
     * 检查字段是否存在
     *
     * @param fileName 字段名
     * @return 存在
     */
    default String getDbField(String fileName) {
        final FilterBeanInfo filterBeanInfo = getFilterBeanInfo();
        final FilterBeanField filterBeanField =
                filterBeanInfo.getSearchBeanFieldMap().get(fileName);
        return filterBeanField.getDbField();
    }

    /**
     * 检查字段数据是否正确
     *
     * @param fileName 字段名
     * @param operator 操作符
     */
    default void checkField(String fileName, String operator) {
        final FilterBeanInfo filterBeanInfo = getFilterBeanInfo();
        final FilterBeanField filterBeanField =
                filterBeanInfo.getSearchBeanFieldMap().get(fileName);
        if (StringUtils.isBlank(operator)) {
            throw new DetailedIllegalArgumentException(String.format("%s operator is null", fileName));
        }
        if (!filterBeanField.getOnlyType().contains(Operator.from(operator))) {
            throw new DetailedIllegalArgumentException(String.format("%s field not supported %s ", fileName, operator));
        }
    }

    FilterBeanInfo getFilterBeanInfo();
}
