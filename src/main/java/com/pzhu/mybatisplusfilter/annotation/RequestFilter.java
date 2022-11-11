package com.pzhu.mybatisplusfilter.annotation;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface RequestFilter {
    Class<?> filterClass();
}
