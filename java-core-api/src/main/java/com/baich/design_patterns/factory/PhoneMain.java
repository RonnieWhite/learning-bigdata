package com.baich.design_patterns.factory;

import com.baich.design_patterns.factory.normal.NormalIPhoneFactory;
import com.baich.design_patterns.factory.normal.NormalPhone;
import com.baich.design_patterns.factory.normal.NormalPhoneFactory;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-12
 * Time : 10:25
 * Description:
 * Modified By:
 * version : v1.0
 */
public class PhoneMain {
    public static void main(String[] args) {
        // simple
/*        SimplePhoneFactory factory = new SimplePhoneFactory();
        SimplePhone phone = factory.getPhone("mi");
        phone.make();*/
// normal
        NormalPhoneFactory factory = new NormalIPhoneFactory();
        NormalPhone phone = factory.makePhone();
        phone.make();
    }
}
