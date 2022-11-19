package com.pzhu.filter.enums;

import com.pzhu.filter.function.Convert;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum TestEnum implements Convert<String> {
    test("名字", "test");

    private static final Map<String, TestEnum> enumMap;

    static {
        enumMap = Arrays.stream(values()).collect(Collectors.toMap(value -> value.name, value -> value));
    }

    private final String name;
    private final String dec;

    TestEnum(String name, String dec) {
        this.name = name;
        this.dec = dec;
    }

    @Override
    public String convert(String text) {
        return enumMap.get(text).dec;
    }
}