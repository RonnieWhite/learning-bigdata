package com.baich.utils;

import org.apache.commons.codec.binary.Base64;

import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-08-09
 * Time : 16:38
 * Description:
 * Modified By:
 * version : v1.0
 */
public class AuthUtils {
    /**
     * @param secureId
     * @param timestamp
     * @param randomValue
     * @param secureKey
     * @return
     */
    private static String generateSignature(String secureId, long timestamp, int randomValue, String secureKey) {
        Base64 base64 = new Base64();
        byte[] baseStr = base64.encode(HmacUtils.hmacSha1(secureKey, secureId + timestamp + randomValue));
        String result = "";
        try {
            result = URLEncoder.encode(new String(baseStr), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param secureId
     * @param secureKey --header "tbds-auth:a b c d"
     */
    public static String tbdsAuth(String secureId, String secureKey) {
        long timestamp = System.currentTimeMillis();
        int randomValue = new Random().nextInt(100);
        String signature = generateSignature(secureId, timestamp, randomValue, secureKey);
        List<Object> array = new ArrayList<>();
        array.add("TBDS");
        array.add(secureId);
        array.add(timestamp);
        array.add(randomValue);
        array.add(signature);
        return StringUtils.join(array, " ");
    }
}
