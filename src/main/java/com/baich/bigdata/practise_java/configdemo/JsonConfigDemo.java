package com.baich.bigdata.practise_java.configdemo;

import java.io.InputStream;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-23
 * Time : 17:06
 * Description:
 * Modified By:
 * version : v1.0
 */
public class JsonConfigDemo {
    private static final String configFileName = "demo.json";
    public static void main(String[] args) {

        InputStream stream = JsonConfigDemo.class.getClassLoader().getResourceAsStream(configFileName);


    }
}
