package com.baich.bigdata.learning_hadoop.cn.test01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class testInfoBean {
    public static void main(String[] args) throws Exception {
        File file = new File("E:/a.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.print(line + "\t");
        }
    }
}
