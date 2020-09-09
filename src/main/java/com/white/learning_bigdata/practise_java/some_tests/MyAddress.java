package com.white.learning_bigdata.practise_java.some_tests;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-09-08
 * Time : 15:22
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MyAddress {
    public static void getLocalHost() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println(inetAddress.toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getLocalHost();
    }
}
