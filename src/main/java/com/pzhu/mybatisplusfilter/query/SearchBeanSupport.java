package com.pzhu.mybatisplusfilter.query;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.pzhu.mybatisplusfilter.enums.Operator;
import com.pzhu.mybatisplusfilter.exception.DetailedIllegalArgumentException;
import com.pzhu.mybatisplusfilter.metadata.SearchBeanField;
import com.pzhu.mybatisplusfilter.metadata.SearchBeanInfo;

/**
 * 提供一些searchBean的扩展支持
 * @author 75073
 */
public interface SearchBeanSupport {

    /**
     * 检查字段是否存在
     *
     * @param fileName 字段名
     * @return 存在
     */
    default String getDbField(String fileName) {
        final SearchBeanInfo searchBeanInfo = getSearchBeanInfo();
        final SearchBeanField searchBeanField =
                searchBeanInfo.getSearchBeanFieldMap().get(fileName);
        return searchBeanField.getDbField();
    }

    /**
     * 检查字段数据是否正确
     *
     * @param fileName 字段名
     * @param operator 操作符
     * @return 正确
     */
    default void checkField(String fileName, String operator) {
        final SearchBeanInfo searchBeanInfo = getSearchBeanInfo();
        final SearchBeanField searchBeanField =
                searchBeanInfo.getSearchBeanFieldMap().get(fileName);
        if (StringUtils.isBlank(operator)) {
            throw new DetailedIllegalArgumentException(String.format("%s operator is null", fileName));
        }
        if (!searchBeanField.getOnlyType().contains(Operator.from(operator))) {
            throw new DetailedIllegalArgumentException(String.format("%s field not supported %s ", fileName, operator));
        }
    }

    SearchBeanInfo getSearchBeanInfo();
}
