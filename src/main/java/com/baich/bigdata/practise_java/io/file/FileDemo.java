package com.baich.bigdata.practise_java.io.file;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-08-04
 * Time : 10:19
 * Description:
 * Modified By:
 * version : v1.0
 */
public class FileDemo {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(dateFormat.parse("2021804").getTime());
        System.out.println(date);
    }
}
