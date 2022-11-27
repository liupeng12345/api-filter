package com.pzhu.filter;

import com.pzhu.filter.annotation.CanOrderBy;
import com.pzhu.filter.annotation.Field;
import com.pzhu.filter.enums.Operator;
import lombok.Data;

@Data
public class UserSearch {
    @Field(
            onlyOn = {Operator.NULL, Operator.START_WITH, Operator.EQUAL, Operator.START_WITH, Operator.END_WITH})
    @CanOrderBy
    private String name;

    @CanOrderBy
    private int age;
}
