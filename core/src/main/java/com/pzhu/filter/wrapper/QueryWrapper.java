package com.pzhu.filter.wrapper;

import lombok.Data;

@Data
public class QueryWrapper {
    private String filter;
    private String orderBy;
    private String searchList;
    private Integer pageSize;
}
