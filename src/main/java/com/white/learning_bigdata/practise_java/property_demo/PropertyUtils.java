package com.white.learning_bigdata.practise_java.property_demo;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Properties;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-09-08
 * Time : 14:26
 * Description:
 * Modified By:
 * version : v1.0
 */
public class PropertyUtils {
    private static final Properties PROPERTIES = new Properties();

    public static Properties getProperties() {
        return PROPERTIES;
    }

    public static void init() {
        init("log4j.properties");

    }

    public static void init(String propertiesName) {
        URL propertiesURL = PropertyUtils.class.getClassLoader().getResource(propertiesName);
        Objects.requireNonNull(propertiesURL);
        try {
            InputStream is = propertiesURL.openStream();
            PROPERTIES.load(is);
        } catch (IOException e) {
            System.out.println("初始化配置文件异常");
            ExceptionUtils.rethrow(e);
        } finally {
            Enumeration<Object> keys = PROPERTIES.keys();
            while (keys.hasMoreElements()) {
//                keys.nextElement();
                String key = keys.nextElement().toString();
                String value = PROPERTIES.getProperty(key);
                System.out.println(key + ": " + value);
            }
        }
    }

    public static void main(String[] args) {
        init();
    }
}
