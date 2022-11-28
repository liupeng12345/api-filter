package com.pzhu.filter.search;

import com.pzhu.filter.annotation.CanOrderBy;
import com.pzhu.filter.annotation.Field;
import com.pzhu.filter.annotation.FilterBean;
import com.pzhu.filter.enums.Operator;
import com.pzhu.filter.enums.TestEnum;
import lombok.Data;

import java.time.Instant;

@Data
@FilterBean(select = "u.name, u.age", tables = "user u ", autoMapTo = "u")
public class UserSearch {

    @Field(
            value = "u.name",
            onlyOn = {Operator.NULL, Operator.START_WITH, Operator.EQUAL, Operator.START_WITH, Operator.END_WITH},
            convertClass = TestEnum.class)
    @CanOrderBy
    private String name;

    @CanOrderBy
    private int age;

    private Instant dateCreated;
}
