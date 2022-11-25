package com.pzhu.filter.utils;

import com.pzhu.filter.enums.Operator;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class LambdaUtilsTest {

    @Data
    class User {
        String name;
    }

    @Test
    public void tes2() {
        Filters filters = Filters.and(
                Filters.operator(User::getName, Operator.MULTI_VALUE, new ArrayList<>(Arrays.asList("123", "123"))),
                Filters.operator(User::getName, Operator.CONTAIN, "🐮"));

        System.out.println(filters.getValue());
    }
}