package com.baich.bigdata.javase.jackson;

import com.baich.bigdata.javase.jackson.sub.MysqlConfig;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-24
 * Time : 20:18
 * Description: 映射配置文件的最原始层
 * Modified By:
 * version : v1.0
 */
public class AppConfig {
    private String key01;
    private String key02;
    private MysqlConfig mysqlConfig;

    public String getKey01() {
        return key01;
    }

    public void setKey01(String key01) {
        this.key01 = key01;
    }

    public String getKey02() {
        return key02;
    }

    public void setKey02(String key02) {
        this.key02 = key02;
    }

    public MysqlConfig getMysqlConfig() {
        return mysqlConfig;
    }

    public void setMysqlConfig(MysqlConfig mysqlConfig) {
        this.mysqlConfig = mysqlConfig;
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "key01='" + key01 + '\'' +
                ", key02='" + key02 + '\'' +
                ", mysqlConfig=" + mysqlConfig +
                '}';
    }
}
