package main.java.practise.review;

import java.io.*;

public class io_review2 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("E:/data/a.txt");
        File outputFile = new File("E:/data/b.txt");
        if (!inputFile.exists()) {
            System.out.println("无输入文件，运行失败！");
            System.exit(-1);
        }
        int len = 0;
        char[] chars = new char[1024];
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));
        while ((len = br.read(chars)) != -1) {
            bw.write(chars, 0, len);
        }
        bw.close();
        br.close();
    }
}
