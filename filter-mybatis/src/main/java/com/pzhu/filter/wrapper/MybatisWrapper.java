package com.pzhu.filter.wrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.filter.utils.StringUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class MybatisWrapper extends QueryWrapper {
    private static final String WHERE = "where %s";

    private static final String ORDER_BY = "order by %s";
    private String orderBySql;
    private String whereSql;
    private Page<?> page;
    private Map<String, Object> paramNameValuePairs;

    public static String formatWhereSql(String whereStr) {
        return Optional.ofNullable(whereStr)
                .filter(StringUtils::isNotBlank)
                .map(WHERE::formatted)
                .orElse("");
    }

    public static String formatOrderBySql(String orderStr) {
        return Optional.ofNullable(orderStr)
                .filter(StringUtils::isNotBlank)
                .map(ORDER_BY::formatted)
                .orElse("");
    }
}
