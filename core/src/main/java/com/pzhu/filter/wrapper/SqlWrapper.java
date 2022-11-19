package com.pzhu.filter.wrapper;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class SqlWrapper extends QueryWrapper {

    @Getter
    @Setter
    protected Map<String, Object> paramNameValuePairs = new HashMap<>();

    @Setter
    private String whereSql;

    @Setter
    private String orderBySql;

    @Setter
    @Getter
    private String sqlComment;

    @Setter
    @Getter
    private String searchList;

    public String getWhereSql() {
        if (StringUtils.isNotBlank(whereSql)) {
            return String.format(" where %s ", whereSql);
        }
        return "";
    }

    public String getOrderBySql() {
        if (StringUtils.isNotBlank(orderBySql)) {
            return String.format(" order by %s ", orderBySql);
        }
        return "";
    }

    public String getSqlSegment() {
        return getWhereSql() + getOrderBySql();
    }
}
