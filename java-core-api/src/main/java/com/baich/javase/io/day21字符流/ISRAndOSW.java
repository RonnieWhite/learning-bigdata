package com.baich.javase.io.day21字符流;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class ISRAndOSW {
    private File inFile = new File("E:/data/javaIO/a.csv");
    private File outFile = new File("E:/data/javaIO/b.csv");

    @Before
    public void init() throws IOException {
        if (!outFile.exists()) {
            boolean res = outFile.createNewFile();
            System.out.println(res);
        }
    }

    // 共耗时115毫秒。
    @Test
    public void NoBufferedChar() throws IOException {
        long start = System.currentTimeMillis();
        InputStreamReader isr = new InputStreamReader(new FileInputStream(inFile), "UTF-8");
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outFile), "GBK");
        char[] chars = new char[1024];
        int len = 0;
        while ((len = isr.read(chars)) != -1) {
            osw.write(chars, 0, len);
        }
        osw.close();
        isr.close();
        long end = System.currentTimeMillis();
        System.out.println("共耗时" + (end - start) + "毫秒。");
    }

    // 共耗时85毫秒。
    @Test
    public void BufferedChars() throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inFile), "UTF-8"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "GBK"));
        int len = 0;
        char[] chars = new char[1024];
        while ((len = br.read(chars)) != -1) {
            bw.write(chars, 0, len);
        }
        bw.close();
        br.close();
        long end = System.currentTimeMillis();
        System.out.println("共耗时" + (end - start) + "毫秒。");
    }


}
