package com.baich.javase.nio.selector;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-08-13
 * Time : 11:07
 * Description:
 * Modified By:
 * version : v1.0
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class WebClient {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));

            ByteBuffer writeBuffer = ByteBuffer.allocate(32);
            ByteBuffer readBuffer = ByteBuffer.allocate(32);

            writeBuffer.put("hello".getBytes());
            writeBuffer.flip();

            while (true) {
//                writeBuffer.rewind();
                socketChannel.write(writeBuffer);
                readBuffer.clear();
                socketChannel.read(readBuffer);
                writeBuffer.compact();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
