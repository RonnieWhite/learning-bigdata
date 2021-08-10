package com.baich.bigdata.review;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-18
 * Time : 16:48
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Entry {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("D:/data/data.txt", "r");
        FileChannel channel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (channel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            while (byteBuffer.hasRemaining())
                System.out.println((char) byteBuffer.get());
            byteBuffer.compact();
        }
        channel.close();
        file.close();
    }
}