package com.pzhu.mybatisplusfilter.enums;

import com.pzhu.mybatisplusfilter.function.Convert;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum TestEnum implements Convert<String> {
    test("名字","test");

    private final String name;

    private final String dec;

    private static Map<String, TestEnum> enumMap;

    static {
        enumMap = Arrays.stream(values()).collect(Collectors.toMap(value -> value.name, value -> value));
    }

    @Override
    public String convert(String text) {
        return enumMap.get(text).dec;
    }
}
