package com.baich.bigdata.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-25
 * Time : 18:55
 * Description:
 * Modified By:
 * version : v1.0
 */
public class PrintDemo {
    public static void main(String[] args) {
        try {
            Class.forName("com.baich.bigdata.design_patterns.strategy.Duck");
        } catch (ClassNotFoundException e) {
            // 直接打印到控制台
            e.printStackTrace();
            // 存入一个字节流中
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            // 此时，baos拥有所有异常信息日志,如要取出，直接toString()
            // 也就是 ystem.out.println(baos.toString()); = e.printStackTrace();
            System.out.println(baos.toString());
        }
    }
}
