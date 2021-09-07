package com.baich.bigdata.utils.apachehttpclient;

import com.baich.bigdata.utils.apachehttpclient.ssl.IgnoreSSLHttpClientFactory;
import lombok.SneakyThrows;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-09-07
 * Time : 15:13
 * Description:
 * Modified By:
 * version : v1.0
 */
public class ApacheHttpRequestUtils {


    private static CloseableHttpClient getHttpClient() {
        CloseableHttpClient httpClient = null;
        try {
            httpClient = IgnoreSSLHttpClientFactory.createIgnoreSSLHttpClientFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpClient;

    }

    public static String doGet(String url) {
        CloseableHttpClient httpClient = getHttpClient();
        if (httpClient == null) return "";
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Content-Type", "application/json");
        httpGet.addHeader("Authorization", "auth key");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            return handleResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(response, httpClient);
        }
        return "";
    }

    public static String doPost(String url, String jsonStr) {
        CloseableHttpClient httpClient = getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Authorization", "auth key");
        CloseableHttpResponse response = null;
        try {
            httpPost.setEntity(new StringEntity(jsonStr));
            response = httpClient.execute(httpPost);
            return handleResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close(response, httpClient);
            }
        }
        return "";
    }

    private static String handleResponse(CloseableHttpResponse response) throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return "";
    }

    private static void close(CloseableHttpResponse response, CloseableHttpClient httpClient) {
        if (response != null) {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
