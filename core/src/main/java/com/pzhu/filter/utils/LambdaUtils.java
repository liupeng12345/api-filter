package com.pzhu.filter.utils;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

import static java.lang.Character.toLowerCase;

/**
 * 处理函数的方法
 */
public class LambdaUtils {
    @FunctionalInterface
    public interface SFunction<T, R> extends Function<T, R>, Serializable {}

    public static <T, R> String getFunctionName(SFunction<T, R> function) {
        try {
            Method method = function.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda lambda = (SerializedLambda) method.invoke(function);
            String functionName = lambda.getImplMethodName().substring("get".length());
            return getFiledName(functionName);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getFiledName(String functionName) {
        char[] chars = functionName.toCharArray();
        chars[0] = toLowerCase(chars[0]);
        return String.valueOf(chars);
    }
}
