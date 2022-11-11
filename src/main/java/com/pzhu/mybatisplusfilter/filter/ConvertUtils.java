package com.pzhu.mybatisplusfilter.filter;

import com.pzhu.mybatisplusfilter.function.Convert;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

public class ConvertUtils {

    private static final ConcurrentHashMap<Class<?>, Class<? extends Convert<?>>> convertMap =
            new ConcurrentHashMap<>();

    static {
        convertMap.put(String.class, StringConvert.class);
        convertMap.put(Long.class, LongConvert.class);
        convertMap.put(long.class, LongConvert.class);
        convertMap.put(Byte.class, ByteConvert.class);
        convertMap.put(byte.class, ByteConvert.class);
        convertMap.put(Integer.class, IntegerConvert.class);
        convertMap.put(int.class, IntegerConvert.class);
        convertMap.put(BigDecimal.class, BigDecimalConvert.class);
        convertMap.put(Double.class, DoubleConvert.class);
        convertMap.put(double.class, DoubleConvert.class);
        convertMap.put(Float.class, FloatConvert.class);
        convertMap.put(float.class, FloatConvert.class);
        convertMap.put(LocalDateTime.class, LocalDateTimeConvert.class);
        convertMap.put(LocalDate.class, LocalDateConvert.class);
        convertMap.put(Boolean.class, BooleanConvert.class);
        convertMap.put(boolean.class, BooleanConvert.class);
        convertMap.put(Short.class, ShortConvert.class);
        convertMap.put(short.class, ShortConvert.class);
        convertMap.put(Instant.class, InstantConvert.class);
    }

    public static Convert<?> of(Class<?> fieldType) {
        try {
            final Class<? extends Convert<?>> aClass = convertMap.get(fieldType);
            if (aClass != null) {
                return aClass.getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ClassCastException();
        } catch (InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static class StringConvert implements Convert<String> {
        @Override
        public String convert(String text) {
            return text;
        }
    }

    public static class LongConvert implements Convert<Long> {

        @Override
        public Long convert(String text) {
            return Long.valueOf(text);
        }
    }

    public static class ByteConvert implements Convert<Byte> {

        @Override
        public Byte convert(String text) {
            return Byte.valueOf(text);
        }
    }

    public static class IntegerConvert implements Convert<Integer> {

        @Override
        public Integer convert(String text) {
            return Integer.valueOf(text);
        }
    }

    public static class BigDecimalConvert implements Convert<BigDecimal> {

        @Override
        public BigDecimal convert(String text) {
            return new BigDecimal(text);
        }
    }

    public static class DoubleConvert implements Convert<Double> {

        @Override
        public Double convert(String text) {
            return Double.valueOf(text);
        }
    }

    public static class FloatConvert implements Convert<Float> {
        @Override
        public Float convert(String text) {
            return Float.valueOf(text);
        }
    }

    public static class LocalDateTimeConvert implements Convert<LocalDateTime> {
        @Override
        public LocalDateTime convert(String text) {
            return LocalDateTime.parse(text);
        }
    }

    public static class LocalDateConvert implements Convert<LocalDate> {

        @Override
        public LocalDate convert(String text) {
            return LocalDate.parse(text);
        }
    }

    public static class BooleanConvert implements Convert<Boolean> {

        @Override
        public Boolean convert(String text) {
            return Boolean.parseBoolean(text);
        }
    }

    public static class ShortConvert implements Convert<Short> {
        @Override
        public Short convert(String text) {
            return Short.valueOf(text);
        }
    }

    public static class InstantConvert implements Convert<Instant> {
        @Override
        public Instant convert(String text) {
            return Instant.parse(text);
        }
    }
}
