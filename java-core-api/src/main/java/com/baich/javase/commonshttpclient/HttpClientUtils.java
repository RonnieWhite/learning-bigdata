package com.baich.javase.commonshttpclient;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-09-03
 * Time : 20:43
 * Description:
 * Modified By:
 * version : v1.0
 */

/*
 * 利用HttpClient进行post请求的工具类
 */
public class HttpClientUtils {

    public static String doGet(String url) throws Exception {
        //声明
        ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
        //加入相关的https请求方式
        Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
        //发送请求即可
        org.apache.commons.httpclient.HttpClient httpclient = new org.apache.commons.httpclient.HttpClient();
        GetMethod httpget = new GetMethod(url);
        System.out.println("======url:" + url);
        try {
            httpclient.executeMethod(httpget);
            return httpget.getResponseBodyAsString();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        } finally {
            httpget.releaseConnection();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(doGet("http://www.baidu.com"));
    }

}
