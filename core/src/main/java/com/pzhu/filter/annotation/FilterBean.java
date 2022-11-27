package com.pzhu.filter.annotation;

import java.lang.annotation.*;

/**
 *
 *
 * @author 75073
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface FilterBean {

    String select() default "";

    String tables() default "";

    String autoMapTo() default "";
}
