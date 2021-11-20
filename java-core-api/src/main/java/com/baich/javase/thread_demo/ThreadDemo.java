package com.baich.javase.thread_demo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-08-12
 * Time : 20:34
 * Description:
 * Modified By:
 * version : v1.0
 */
public class ThreadDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("D:/data/data.txt", "r");
        FileChannel channel = file.getChannel();
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        while (channel.read(allocate) != -1) {
            allocate.flip();
            while (allocate.hasRemaining()) {
                System.out.print((char) allocate.get());
            }
            allocate.compact();
        }
        channel.close();
        file.close();
    }
}
