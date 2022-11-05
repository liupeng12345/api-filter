package com.pzhu.mybatisplusfilter.search;

import com.pzhu.mybatisplusfilter.annotation.CanOrderBy;
import com.pzhu.mybatisplusfilter.annotation.DbField;
import com.pzhu.mybatisplusfilter.annotation.SearchBean;
import com.pzhu.mybatisplusfilter.enums.Operator;
import com.pzhu.mybatisplusfilter.enums.TestEnum;
import lombok.Data;

import java.time.Instant;

@Data
@SearchBean(tables = "user u ", autoMapTo = "u")
public class UserSearch {

    @DbField(
            value = "u.name",
            onlyOn = {Operator.NULL, Operator.START_WITH, Operator.EQUAL, Operator.START_WITH},
            convertClass = TestEnum.class)
    @CanOrderBy
    private String name;

    @CanOrderBy
    private int age;

    private Instant dateCreated;
}
