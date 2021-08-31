package com.baich.bigdata.javase.configdemo;

import com.baich.bigdata.javase.configdemo.subconfig.MysqlConfig;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-08-31
 * Time : 11:47
 * Description:
 * Modified By:
 * version : v1.0
 */
public class ConfigPojo {
    private String key01;
    private String key02;
    private MysqlConfig mysqlConfig;

    public String getKey01() {
        return key01;
    }

    public String getKey02() {
        return key02;
    }

    public MysqlConfig getMysqlConfig() {
        return mysqlConfig;
    }

    @Override
    public String toString() {
        return "ConfigPojo{" +
                "key01='" + key01 + '\'' +
                ", key02='" + key02 + '\'' +
                ", mysqlConfig=" + mysqlConfig +
                '}';
    }
}
