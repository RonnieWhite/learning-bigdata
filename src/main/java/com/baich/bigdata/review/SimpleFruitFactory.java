package com.baich.bigdata.review;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-18
 * Time : 16:42
 * Description:
 * Modified By:
 * version : v1.0
 */
public class SimpleFruitFactory {
    private String type;

    public SimpleFruitFactory(String type) {
        this.type = type;
    }

    public Showable create() {
        switch (type) {
            case "Apple":
                return new Apple();
            case "Banana":
                return new Banana();
            default:
                return null;
        }
    }
}
