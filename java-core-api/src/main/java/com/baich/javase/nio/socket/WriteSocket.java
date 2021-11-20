package com.baich.javase.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-08-11
 * Time : 22:55
 * Description:
 * Modified By:
 * version : v1.0
 */
public class WriteSocket {
    public static void main(String[] args) throws IOException {
        SocketAddress address = new InetSocketAddress("vm01", 9999);
        SocketChannel channel = SocketChannel.open(address);
        ByteBuffer wrap = ByteBuffer.wrap("hello".getBytes());
        channel.write(wrap);
        channel.close();
    }
}
