package com.pzhu.mybatisplusfilter.annotation;


import java.lang.annotation.*;

/**
 *
 *
 * @author 75073
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SearchBean {

    String tables() default "";

    String autoMapTo() default "";
}

