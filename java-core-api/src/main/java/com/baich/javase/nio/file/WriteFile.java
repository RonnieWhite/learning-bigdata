package com.baich.javase.nio.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class WriteFile {
    public static void main(String[] args) throws IOException {
        // 获取文件，"rw"为读写模式
        RandomAccessFile file = new RandomAccessFile("D:/data/data.txt", "rw");
        // 获取文件通道
        FileChannel channel = file.getChannel();
        // 分配一个容量为1024的字节缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 循环从通道中读取内容到字节缓冲区
        byteBuffer.put("hello".getBytes());
        //将字节缓冲区的position置为0，limit置为position，也就是将缓冲区由读入模式切换为写出模式。
        byteBuffer.flip();
        // 写出
        channel.write(byteBuffer);
        channel.close();
        file.close();
    }
}
