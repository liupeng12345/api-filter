package com.pzhu.filter.annotation;

import java.lang.annotation.*;

/**
 * @author 75073
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SearchMapper {
    Class<?> value();
}
