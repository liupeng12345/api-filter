package com.pzhu.mybatisplusfilter.annotation;

/**
 * 表示字段可以排序
 */

import java.lang.annotation.*;

/**
 * 用于注解一个可检索 bean 的属性
 *
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CanOrderBy {

}
