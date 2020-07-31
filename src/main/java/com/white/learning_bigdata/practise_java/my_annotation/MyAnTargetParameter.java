package com.white.learning_bigdata.practise_java.my_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-07-31
 * Time : 14:44
 * Description:
 * Modified By:
 * version : v1.0
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnTargetParameter {
    String value() default "This is a default value defined on parameter";
}
