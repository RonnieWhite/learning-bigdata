package com.baich.bigdata.practise_java.net;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-15
 * Time : 15:09
 * Description:
 * Modified By:
 * version : v1.0
 */
public class RequestDemo {
    public static void main(String[] args) throws Exception {
        doGet();
        doPost1();
        doPost2();
    }

    private static String doGet() throws Exception {
        String urlStr = "http://localhost:8080/test/testGet?userName=user001";
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String line = null;
        StringBuilder result = new StringBuilder();
        while ((line = br.readLine()) != null) {
            result.append(line).append("\n");
        }
        conn.disconnect();
        return result.toString();
    }

    // 请求springboot中controller入参形式为：@RequestBody Map<String,String> params 的接口
    // 如果请求形如@RequestBody String params 入参的接口，则不需要objectMapper，直接：pw.write("xxxx")
    private static String doPost1() throws Exception {
        String urlStr = "http://localhost:8080/test/testPost1";
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> params = new HashMap<>();
        params.put("myKey", "myValue");
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(conn.getOutputStream()));
        ObjectMapper objectMapper = new ObjectMapper();
        pw.write(objectMapper.writeValueAsString(params));
        pw.flush();
        pw.close();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String line = null;
        StringBuilder result = new StringBuilder();
        while ((line = br.readLine()) != null) {
            result.append(line).append("\n");
        }
        return result.toString();
    }

    // 如果请求形如@RequestParam(name="paramInfo" String paramInfo)入参的接口，则不需要objectMapper，直接：pw.write("paramInfo=xxx")即可
    private static String doPost2() throws Exception {
        String urlStr = "http://localhost:8080/test/testPost2";
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        // 这里留意，不再是conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(conn.getOutputStream()));
        pw.write("paramInfo=xxx");
        pw.flush();
        pw.close();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String line = null;
        StringBuilder result = new StringBuilder();
        while ((line = br.readLine()) != null) {
            result.append(line).append("\n");
        }
        return result.toString();
    }
}
