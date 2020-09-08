package com.white.learning_bigdata.practise_java.lesson.day21字符流;

import org.junit.Test;

import java.io.*;

public class 字符流 {
    @Test
    public void run() throws IOException {
        File inFile = new File("E:/data/a.txt");
        File outFile = new File("E:/data/b.txt");
        if (!outFile.exists()) {
            boolean res = outFile.createNewFile();
            System.out.println(res);
        }
        InputStreamReader isr = new InputStreamReader(new FileInputStream(inFile), "UTF-8");
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8");
        int ch = 0;
        while ((ch = isr.read()) != -1) {
            osw.write(ch);
        }
        isr.close();
        osw.close();
    }

    @Test
    public void run2() throws IOException {
        File infile = new File("E:/data/a.txt");
        File outfile = new File("E:/data/b.txt");
        InputStreamReader isr = new InputStreamReader(new FileInputStream(infile), "UTF-8");
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outfile), "UTF-8");
        int len = 0;
        char[] chars = new char[1024];
        while ((len = isr.read(chars)) != -1) {
            System.out.println(len);
            osw.write(chars, 0, len);
        }
        osw.close();
        isr.close();
    }
}
