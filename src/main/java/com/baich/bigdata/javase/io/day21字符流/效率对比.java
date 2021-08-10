package com.baich.bigdata.javase.io.day21字符流;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class 效率对比 {
    private File inFile = new File("E:/data/javaIO/a.csv");
    private File outFile = new File("E:/data/javaIO/b.csv");

    @Before
    public void init() throws IOException {
        if (!outFile.exists()) {
            boolean res = outFile.createNewFile();
            System.out.println(res);
        }
    }

    // 共耗时541毫秒。
    @Test
    public void NoBufferedChar() throws IOException {
        long start = System.currentTimeMillis();
        FileReader fr = new FileReader(inFile);
        FileWriter fw = new FileWriter(outFile);
        int ch = 0;
        while ((ch = fr.read()) != -1) {
            fw.write(ch);
        }
        fw.close();
        fr.close();
        long end = System.currentTimeMillis();
        System.out.println("共耗时" + (end - start) + "毫秒。");
    }

    // 共耗时87毫秒。
    @Test
    public void NoBufferedChars() throws IOException {
        long start = System.currentTimeMillis();
        FileReader fr = new FileReader(inFile);
        FileWriter fw = new FileWriter(outFile);
        int ch = 0;
        char[] chars = new char[1024];
        while ((ch = fr.read(chars)) != -1) {
            fw.write(chars, 0, ch);
        }
        fw.close();
        fr.close();
        long end = System.currentTimeMillis();
        System.out.println("共耗时" + (end - start) + "毫秒。");
    }

    // 共耗时235毫秒。
    @Test
    public void BufferedChar() throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader(inFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
        int ch = 0;
        while ((ch = br.read()) != -1) {
            bw.write(ch);
        }
        bw.close();
        br.close();
        long end = System.currentTimeMillis();
        System.out.println("共耗时" + (end - start) + "毫秒。");
    }

    // 共耗时77毫秒。
    @Test
    public void BufferedChars() throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader(inFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
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
