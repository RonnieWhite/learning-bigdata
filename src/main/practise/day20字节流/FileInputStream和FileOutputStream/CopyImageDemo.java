package main.practise.day20字节流.FileInputStream和FileOutputStream;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CopyImageDemo {

    @Test
    public void ci1() throws Exception {
        File infile = new File("E:/data/javaIO/鸟.jpg");
        File outfile = new File("E:/data/javaIO/大鸟.jpg");
        FileInputStream fis = new FileInputStream(infile);
        FileOutputStream fos = new FileOutputStream(outfile);
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }
        fos.close();
        fis.close();
    }
}
