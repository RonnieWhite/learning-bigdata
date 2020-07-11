package com.white.learning_bigdata.practise_java.day20字节流.FileInputStream和FileOutputStream;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamDemo {
    private static File file = new File("E:/data/javaIO/a.txt");

    @Test
    public void fos1() throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write("Life is short\n".getBytes());
        fos.write("I use python".getBytes());
        fos.close();
    }

    @Test
    public void fos2() throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        byte[] bytes = {97, 98, 99, 100, 101};
        fos.write(bytes);
        fos.close();
    }

    @Test
    public void fos3() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write("Life is short".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {

                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
