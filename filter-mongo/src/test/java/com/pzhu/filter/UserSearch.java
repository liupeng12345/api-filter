package com.pzhu.filter;

import com.pzhu.filter.annotation.CanOrderBy;
import com.pzhu.filter.annotation.DbField;
import com.pzhu.filter.enums.Operator;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
public class UserSearch {
    @DbField(
            onlyOn = {Operator.NULL, Operator.START_WITH, Operator.EQUAL, Operator.START_WITH, Operator.END_WITH})
    @CanOrderBy
    private String name;

    @CanOrderBy
    private int age;
}