package com.baich.bigdata.javase.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-08-11
 * Time : 22:42
 * Description:
 * Modified By:
 * version : v1.0
 */
public class ReadSocket {
    public static void main(String[] args) throws IOException {
        SocketAddress address = new InetSocketAddress("vm01", 9999);
        SocketChannel channel = SocketChannel.open(address);
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        while (channel.read(allocate) != -1) {
            allocate.flip();
            while (allocate.hasRemaining())
                System.out.print((char) allocate.get());
            allocate.compact();
        }
        channel.close();
    }
}
