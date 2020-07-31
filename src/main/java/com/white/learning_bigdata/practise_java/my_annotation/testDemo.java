package com.white.learning_bigdata.practise_java.my_annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-07-31
 * Time : 14:35
 * Description:
 * Modified By:
 * version : v1.0
 */
@MyAnTargetType
public class testDemo {
    @MyAnTargetField
    private String field = "This is a field";

    @MyAnTargetMethod("test method..")
    public void test(@MyAnTargetParameter String args) {
        System.out.println("参数值=" + args);
    }


    public static void main(String[] args) {
        // 获取类上的注解MyAnTargetType
        MyAnTargetType t = testDemo.class.getAnnotation(MyAnTargetType.class);
        System.out.println("类上的注解值=" + t.value());
        MyAnTargetMethod tm = null;
        try {
            Method method = testDemo.class.getDeclaredMethod("test", String.class);
            tm = method.getAnnotation(MyAnTargetMethod.class);
            System.out.println("方法上的注解值=" + tm.value());
            Annotation[][] annotations = method.getParameterAnnotations();
            for (Annotation[] tt : annotations) {
                for (Annotation t1 : tt) {
                    if (t1 instanceof MyAnTargetParameter) {
                        System.out.println("参数上的注解值=" + ((MyAnTargetParameter) t1).value());
                    }
                }
            }
            method.invoke(new testDemo(), "噶变默认参数");
            MyAnTargetField fieldAn = testDemo.class.getDeclaredField("field").getAnnotation(MyAnTargetField.class);
            System.out.println("字段上的注解值=" + fieldAn.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
