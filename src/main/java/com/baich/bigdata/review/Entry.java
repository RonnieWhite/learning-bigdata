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
        RandomAccessFile file = new RandomAccessFile("D:/data/out.txt", "rw");
        FileChannel channel = file.getChannel();
        channel.write(ByteBuffer.wrap("Hello".getBytes()));
        channel.close();
        file.close();
    }
}