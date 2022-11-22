package com.pzhu.filter.wrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class MybatisWrapper extends QueryWrapper {
    private String orderBySql;
    private String whereSql;
    private Page<?> page;
    private Map<String, Object> paramNameValuePairs;
}
