package com.pzhu.mybatisplusfilter.query;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Data
public class SearchWrapper {

    public static final String SEARCH_WRAPPER = "ew";
    protected Map<String, Object> paramNameValuePairs = new HashMap<>();

    private String whereSql;

    private String orderBySql;

    private String sqlComment;

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
