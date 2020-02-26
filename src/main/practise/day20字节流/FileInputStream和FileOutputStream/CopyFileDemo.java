package main.practise.day20字节流.FileInputStream和FileOutputStream;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CopyFileDemo {
    private static File infile = new File("E:/data/javaIO/a.txt");
    private static File outfile = new File("E:/data/javaIO/b.txt");

    // 单个字节的读写
    @Test
    public void cf1() throws Exception {
        FileInputStream fis = new FileInputStream(infile);
        FileOutputStream fos = new FileOutputStream(outfile);
        int by = 0;
        while ((by = fis.read()) != -1) {
            fos.write(by);
        }
        fos.close();
        fis.close();
    }

    // 指定数组长度读写
    @Test
    public void cf2() throws Exception {
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
