package com.baich.learning_bigdata.practise_java.my_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-07-31
 * Time : 14:41
 * Description:
 * Modified By:
 * version : v1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnTargetField {
    String value() default "This is a default value defined on field";
}
