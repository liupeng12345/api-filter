package com.pzhu.filter.annotation;

import com.pzhu.filter.filter.ConvertUtils;
import com.pzhu.filter.enums.Operator;
import com.pzhu.filter.function.Convert;

import java.lang.annotation.*;

/**
 * @author 75073
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface DbField {

    /**
     * 用于指定属性对应数据库的哪张表的哪个字段
     */
    String value() default "";

    /**
     * @return 该字段是否可以被作为检索条件
     */
    boolean conditional() default true;

    /**
     * 用于指定该字段只允许接受的运算符，为空时，表示任意运算符都接受
     */
    Operator[] onlyOn() default {};
    /**
     * 转化方法
     */
    Class<? extends Convert<?>> convertClass() default ConvertUtils.StringConvert.class;
}
