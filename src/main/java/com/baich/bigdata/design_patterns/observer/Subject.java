package com.baich.bigdata.design_patterns.observer;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-27
 * Time : 09:58
 * Description: 主题接口
 * Modified By:
 * version : v1.0
 */
public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
