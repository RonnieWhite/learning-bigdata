package com.baich.javase.configdemo.subconfig;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-08-31
 * Time : 11:49
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MysqlConfig {
    private String host;
    private Integer port;
    private String password;
    private String database;

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return database;
    }

    @Override
    public String toString() {
        return "MysqlConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", password='" + password + '\'' +
                ", database='" + database + '\'' +
                '}';
    }
}
