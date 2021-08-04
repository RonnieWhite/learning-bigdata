package com.baich.bigdata.practise_java.io.day20字节流;

import org.junit.Test;

import java.io.*;

public class IODemo {
    @Test
    public void run() throws IOException {
        long start = System.currentTimeMillis();
        File infile = new File("E:/data/train.csv");
        File outfile = new File("E:/data/copy.csv");
        if (!outfile.exists()) {
            boolean res = outfile.createNewFile();
            System.out.println(res);
        }
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(infile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outfile));
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        bos.close();
        bis.close();
        long end = System.currentTimeMillis();
        System.out.println("共耗时" + (end - start) + "毫秒");
    }
}
