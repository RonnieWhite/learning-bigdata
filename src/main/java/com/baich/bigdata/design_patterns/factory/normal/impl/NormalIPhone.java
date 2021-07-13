package com.baich.bigdata.design_patterns.factory.normal.impl;

import com.baich.bigdata.design_patterns.factory.normal.NormalPhone;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-12
 * Time : 11:01
 * Description:
 * Modified By:
 * version : v1.0
 */
public class NormalIPhone implements NormalPhone {
    @Override
    public void make() {
        System.out.println("Make a iphone...");
    }
}
