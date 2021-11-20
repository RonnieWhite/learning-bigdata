package com.baich.review;

import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-18
 * Time : 16:48
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Entry {
    public static void main(String[] args) throws IOException {
        Path path = new Path("/abcds/asdqw/aasdf");
        System.out.println(path.getName());
    }
}