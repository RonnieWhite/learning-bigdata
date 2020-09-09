package com.baich.bigdata.practise_java.some_tests;


/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-08-18
 * Time : 16:44
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MyClass {
    public void m1(InterfaceDemo interfaceDemo) {
        interfaceDemo.m();
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.m1(() -> {
            System.out.println("hello");
        });
    }
}
