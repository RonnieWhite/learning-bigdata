package com.baich.utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-16
 * Time : 16:09
 * Description: 工具类，接收http请求并以字符串的形式返回响应体
 * Modified By:
 * version : v1.0
 */
public class RequestUtils {
    public static String doGet(String url) {
        return doRequest(url, RequestMethod.GET);
    }

    public static String doGet(String url, String param) {
        return doRequest(url.concat("?").concat(param), RequestMethod.GET);
    }

    public static String doGet(String url, Map<String, Object> params) {
        if (params != null && params.size() > 0) {
            StringJoiner paramJoiner = new StringJoiner("&");
            Set<Map.Entry<String, Object>> entries = params.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                paramJoiner.add(entry.getKey().concat("=").concat(String.valueOf(entry.getValue())));
            }
            return doGet(url, paramJoiner.toString());
        }
        return "";
    }

    public static String doPost(String url, String param) {
        return doRequest(url, RequestMethod.POST,param);
    }

    public static String doPost(String url, Map<String, Object> param) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        String params = objectMapper.writeValueAsString(param);
        return doPost(url, params);
    }


    private static String doRequest(String url, Object... params) {
        ServiceRequest request = null;
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            request = new ServiceRequest(conn);
            if (RequestMethod.POST.equals(params[0])) {
                request.configConn(RequestMethod.POST);
                request.genReqBody((String) params[1]);
            }
            if (RequestMethod.GET.equals(params[0])) {
                request.configConn(RequestMethod.GET);
            }
            return request.fetchRespBody();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.close();
        }
        return "";
    }


    private static class ServiceRequest {
        private HttpURLConnection conn;

        public ServiceRequest(HttpURLConnection conn) {
            this.conn = conn;
        }

        private void configConn(RequestMethod method) throws Exception {
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod(method.name());
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        }

        private void genReqBody(String strParam) throws Exception {
            PrintWriter pw = new PrintWriter(new BufferedOutputStream(conn.getOutputStream()));
            pw.write(strParam);
            pw.flush();
            pw.close();
        }

        private String fetchRespBody() throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line = null;
            StringBuilder respBody = new StringBuilder(1024);
            while ((line = br.readLine()) != null) {
                respBody.append(line).append("\n");
            }
            return respBody.toString();
        }

        private void close() {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private enum RequestMethod {
        GET,
        POST
    }
}
