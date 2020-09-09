package com.baich.bigdata.practise_java.lesson.day20字节流.FileInputStream和FileOutputStream;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamDemo {
    private static File file = new File("E:/data/javaIO/a.txt");

    @Test
    public void fis1() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            int by = 0;
            while ((by = fis.read()) != -1) {
                System.out.print((char) by);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void fis2() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fis.read(bytes)) != -1) {
                System.out.print(new String(bytes, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
