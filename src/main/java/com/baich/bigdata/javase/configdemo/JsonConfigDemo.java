package com.baich.bigdata.javase.configdemo;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
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
    private static final String configFileName = "application.json";

    public static void main(String[] args) throws IOException {

        InputStream stream = JsonConfigDemo.class.getClassLoader().getResourceAsStream(configFileName);
        ObjectMapper objectMapper = new ObjectMapper();
        ConfigPojo configPojo = objectMapper.readValue(stream, ConfigPojo.class);
        System.out.println(configPojo);


    }
}
