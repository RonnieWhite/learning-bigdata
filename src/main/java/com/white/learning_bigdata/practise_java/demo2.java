package com.white.learning_bigdata.practise_java;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-07-29
 * Time : 10:54
 * Description:
 * Modified By:
 * version : v1.0
 */
public class demo2 {
    public static void main(String[] args) {
        String a = "123";
        String b = "abc";
        String s = StringUtils.join(new String[]{a, b}, File.separator);
        System.out.println(s);
    }
}
