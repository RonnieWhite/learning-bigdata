package com.baich.javase.my_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-07-31
 * Time : 14:37
 * Description:
 * Modified By:
 * version : v1.0
 * 定义一个可以注解在函数上的注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnTargetMethod {
    String value() default "This is a default value defined on a method";
}
