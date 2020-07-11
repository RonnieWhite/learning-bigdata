package com.white.learning_bigdata.practise_java.review;

import org.junit.Test;

import java.io.*;

public class io_review {
    private File inFile = new File("E:/data/javaIO/a.csv");
    private File outFile = new File("E:/data/javaIO/b.csv");

    @Test
    public void init() throws IOException {

        if (!outFile.exists()) {
            System.out.println(outFile.createNewFile());
        }
    }

    @Test
    public void NoBufferedByte() throws IOException {
        FileInputStream fis = new FileInputStream(inFile);
        FileOutputStream fos = new FileOutputStream(outFile);
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }
        fos.close();
        fis.close();
    }

    @Test
    public void NoBufferedChar() throws IOException {
        FileReader fr = new FileReader(inFile);
        FileWriter fw = new FileWriter(outFile);
        int len = 0;
        char[] chars = new char[1024];
        while ((len = fr.read(chars)) != -1) {
            fw.write(chars, 0, len);
        }
        fw.close();
        fr.close();
    }

    @Test
    public void BufferedByte() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inFile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outFile));
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        bos.close();
        bis.close();
    }

    @Test
    public void BufferedChar() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
        int len = 0;
        char[] chars = new char[1024];
        while ((len = br.read(chars)) != -1) {
            bw.write(chars, 0, len);
        }
        bw.close();
        br.close();
    }

    public void changeIO() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inFile), "UTF-8"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
        int len = 0;
        char[] chars = new char[1024];
        while ((len = br.read(chars)) != -1) {
            bw.write(chars, 0, len);
        }
        bw.close();
        br.close();
    }
}









