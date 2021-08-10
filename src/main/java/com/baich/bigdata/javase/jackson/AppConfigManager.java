package com.baich.bigdata.javase.jackson;

import com.baich.bigdata.javase.jackson.sub.MysqlConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-24
 * Time : 20:21
 * Description: 生成和管理AppConfig
 * Modified By:
 * version : v1.0
 */
public class AppConfigManager {
    private static final String configFileName = "application.json";
    private static AppConfig appConfig = null;

    private static void loadConfig() {
        InputStream stream = AppConfigManager.class.getClassLoader().getResourceAsStream(configFileName);
        appConfig = json2Object(stream, AppConfig.class);
    }

    public static AppConfig getInstance() {
        if (appConfig == null) {
            synchronized (AppConfig.class) {
                if (appConfig == null) {
                    loadConfig();
                }
            }
        }
        return appConfig;
    }

    private static <T> T json2Object(InputStream stream, Class<T> type) {
        T res = null;
        if (stream != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                res = objectMapper.readValue(stream, type);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }


    /**
     * 测试运行
     *
     * @param args
     */
    public static void main(String[] args) {
        AppConfig instance = AppConfigManager.getInstance();
        System.out.println(instance);
        MysqlConfig mysqlConfig = AppConfigManager.getInstance().getMysqlConfig();
        System.out.println(mysqlConfig);
    }
}
