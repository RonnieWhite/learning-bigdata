package com.baich.bigdata.javase.property_demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-23
 * Time : 09:40
 * Description:
 * Modified By:
 * version : v1.0
 */
public class PropertyDemo {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = PropertyDemo.class.getClassLoader().getResourceAsStream("application.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        String name = properties.getProperty("name.suffix");
//        String name1 = (String) properties.get("name");
        System.out.println(name);
    }
}
