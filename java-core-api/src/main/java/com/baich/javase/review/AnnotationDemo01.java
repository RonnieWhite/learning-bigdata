package com.baich.javase.review;

import java.lang.annotation.*;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2022-04-24
 * Time : 21:24
 * Description:
 * Modified By:
 * version : v1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AnnotationDemo01 {
    String value() default "100";
}
