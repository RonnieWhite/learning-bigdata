package com.baich.javase.testreflect;

import java.util.Date;

/**
 * @Author: Chenghui Bai
 * Date: 2020/10/20 12:23
 * project name: learning-bigdata
 * @PackgeName: com.baich.bigdata.practise_java
 * @ClassName: TestReflect
 * @Version:
 * @Description:
 */
public class TestReflect {
    public Ref ref = new Ref();

    public void say() {
        System.out.println("hello!");
        System.out.println(new Date());
    }


    public static void main(String[] args) throws Exception {
        /**
         Method method = TestReflect.class.getMethod("say");
         TestReflect reflect = new TestReflect();
         method.invoke(reflect);
         */
        System.out.println(new Date().getTime());
        System.out.println(System.currentTimeMillis());
    }
}
