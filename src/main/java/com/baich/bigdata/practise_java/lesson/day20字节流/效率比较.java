package com.baich.bigdata.practise_java.lesson.day20字节流;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class 效率比较 {
    private File inFile = new File("E:/data/javaIO/a.avi");
    private File outFile = new File("E:/data/javaIO/b.avi");

    @Before
    public void init() throws IOException {
        if (!outFile.exists()) {
            boolean res = outFile.createNewFile();
            System.out.println(res);
        }
    }

    // 共耗时115616毫秒
    @Test
    public void NoBufferedByte() throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream(inFile);
        FileOutputStream fos = new FileOutputStream(outFile);
        int by = 0;
        while ((by = fis.read()) != -1) {
            fos.write(by);
        }
        fos.close();
        fis.close();
        long end = System.currentTimeMillis();
        System.out.println("共耗时" + (end - start) + "毫秒");
    }

    // 共耗时237毫秒
    @Test
    public void NoBufferedBytes() throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream(inFile);
        FileOutputStream fos = new FileOutputStream(outFile);
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }
        fos.close();
        fis.close();
        long end = System.currentTimeMillis();
        System.out.println("共耗时" + (end - start) + "毫秒");
    }

    // 共耗时1168毫秒
    @Test
    public void BufferedByte() throws IOException {
        long start = System.currentTimeMillis();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inFile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outFile));
        int by = 0;
        while ((by = bis.read()) != -1) {
            bos.write(by);
        }
        bos.close();
        bis.close();
        long end = System.currentTimeMillis();
        System.out.println("共耗时" + (end - start) + "毫秒");
    }

    // 共耗时137毫秒
    @Test
    public void BufferedBytes() throws IOException {
        long start = System.currentTimeMillis();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inFile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outFile));
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        bos.close();
        bis.close();
        long end = System.currentTimeMillis();
        System.out.println("共耗时" + (end - start) + "毫秒");
    }
}
