package com.baich.javase.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author: Chenghui Bai
 * Date: 2020/10/23 17:07
 * project name: learning-bigdata
 * @PackgeName: com.baich.bigdata.practise_java.socket
 * @ClassName: SocketTest
 * @Version:
 * @Description:
 */
public class SocketTest {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("time-a.nist.gov", 13);
            Scanner in = new Scanner(socket.getInputStream(), "UTF-8");
            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
