package com.pzhu.mybatisplusfilter.function;

@FunctionalInterface
public interface Convert<T> {
    /**
     * 将字符串转化
     */
    T convert(String text);
}
