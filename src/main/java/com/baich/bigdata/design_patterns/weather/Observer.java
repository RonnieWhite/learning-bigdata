package com.baich.bigdata.design_patterns.weather;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-27
 * Time : 10:07
 * Description: 观察者接口
 * Modified By:
 * version : v1.0
 */
public interface Observer {
    public void update(float temp, float humidity, float pressure);
}
