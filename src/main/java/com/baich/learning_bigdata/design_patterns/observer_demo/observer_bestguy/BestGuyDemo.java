package com.baich.learning_bigdata.design_patterns.observer_demo.observer_bestguy;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-08-10
 * Time : 11:16
 * Description:
 * Modified By:
 * version : v1.0
 */
public class BestGuyDemo {
    public static void main(String[] args) {
        BestGuyInfo bestGuyInfo = new BestGuyInfo();
        bestGuyInfo.setBestGuyInfo("Bibi", 9f, (short) 23);
        BestGuyObserver bestGuyObserver = new BestGuyObserver(bestGuyInfo);
        bestGuyObserver.update(bestGuyInfo, 0);
    }
}
