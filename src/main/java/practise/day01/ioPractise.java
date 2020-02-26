package practise.day01;

import java.io.*;

public class ioPractise {
    public static void main(String[] args) throws Exception {
        /**
         BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("E://test1")));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E://test2")));
         String line = br.readLine();
         bw.write(line);
         System.out.println(line);
         br.close();
         bw.close();
         */


        File file = new File("E://test1");
        File file1 = new File("E://test3");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter(file1);
        BufferedWriter bw = new BufferedWriter(fw);
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.print(line + "\t");
            bw.write(line + "\n");
        }
        br.close();
        fr.close();
        bw.close();
        fw.close();

        /**
         File file = new File("E:/test1");
         BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
         String line = null;
         while ((line = br.readLine()) != null) {
         System.out.print(line + "\t");
         }
         */
    }
}
