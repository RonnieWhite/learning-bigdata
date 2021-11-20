package com.baich.design_patterns.factory.simple;

import com.baich.design_patterns.factory.simple.impl.SimpleIPhone;
import com.baich.design_patterns.factory.simple.impl.SimpleMiPhone;
import com.baich.design_patterns.factory.simple.impl.SimpleOppoPhone;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-12
 * Time : 10:21
 * Description:
 * Modified By:
 * version : v1.0
 */
public class SimplePhoneFactory {
    public SimplePhone getPhone(String phoneName) {
        if (phoneName.equalsIgnoreCase("iphone")) {
            return new SimpleIPhone();
        } else if (phoneName.equalsIgnoreCase("mi")) {
            return new SimpleMiPhone();
        } else if (phoneName.equalsIgnoreCase("oppo")) {
            return new SimpleOppoPhone();
        } else {
            return null;
        }
    }
}
