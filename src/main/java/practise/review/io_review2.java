package main.java.practise.review;

import java.io.*;

public class io_review2 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("E:/data/a.txt");
        File outputFile = new File("E:/data/b.txt");
        if (!inputFile.exists()) {
            System.out.println("输入文件不存在，退出！");
            System.exit(-1);
        }
        if (!outputFile.exists()) {
            boolean flag = outputFile.createNewFile();
            if (flag) {
                System.out.println("创建输出文件成功！");
            }
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));
        int len = 0;
        char[] chars = new char[1024];
        while ((len = br.read(chars)) != -1) {
            bw.write(chars, 0, len);
        }
        br.close();
        bw.close();
    }
}
