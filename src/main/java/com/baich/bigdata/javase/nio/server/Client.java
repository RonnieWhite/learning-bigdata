package com.baich.bigdata.javase.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-08-14
 * Time : 11:19
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        SocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
        SocketChannel channel = SocketChannel.open(address);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!"".equals(line) && line != null) {
                Thread.sleep(2000);
                channel.write(StandardCharsets.UTF_8.encode(line));
            }
        }
        channel.close();
    }
}
