package com.baich.javase.nio.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-08-10
 * Time : 14:47
 * Description:
 * Modified By:
 * version : v1.0
 */
public class ReadFile {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("D:/data/data.txt", "r");
        FileChannel channel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (channel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            while (byteBuffer.hasRemaining())
                System.out.print((char) byteBuffer.get());
            byteBuffer.compact();
        }
        channel.close();
        file.close();
    }
}
