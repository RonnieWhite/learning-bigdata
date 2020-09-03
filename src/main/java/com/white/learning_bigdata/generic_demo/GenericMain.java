package com.white.learning_bigdata.generic_demo;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-08-11
 * Time : 10:13
 * Description:
 * Modified By:
 * version : v1.0
 */
public class GenericMain {
    public static void main(String[] args) {
        GenericDemo<String> genericDemo = new GenericDemo<>("Jordan");
        System.out.println(genericDemo.getKey());
    }
}
