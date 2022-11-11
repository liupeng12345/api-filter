package com.pzhu.mybatisplusfilter.enums;

import com.pzhu.mybatisplusfilter.function.Convert;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum TestEnum implements Convert<String> {
    test("名字", "test");

    private static final Map<String, TestEnum> enumMap;

    static {
        enumMap = Arrays.stream(values()).collect(Collectors.toMap(value -> value.name, value -> value));
    }

    private final String name;
    private final String dec;

    @Override
    public String convert(String text) {
        return enumMap.get(text).dec;
    }
}
