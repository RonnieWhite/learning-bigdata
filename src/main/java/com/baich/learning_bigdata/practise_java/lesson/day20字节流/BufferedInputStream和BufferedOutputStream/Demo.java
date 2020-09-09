package com.baich.learning_bigdata.practise_java.lesson.day20字节流.BufferedInputStream和BufferedOutputStream;

import org.junit.Test;

import java.io.*;

public class Demo {
    @Test
    public void copyFile() throws IOException {
        File inFile = new File("E:/data/javaIO/a.txt");
        File outFile = new File("E:/data/javaIO/c.txt");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inFile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outFile));
        int by = 0;
        while ((by = bis.read()) != -1) {
            bos.write(by);
        }
        bos.close();
        bis.close();
    }
}
