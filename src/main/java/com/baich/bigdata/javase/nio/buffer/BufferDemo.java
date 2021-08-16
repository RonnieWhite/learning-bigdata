package com.baich.bigdata.javase.nio.buffer;

import java.nio.ByteBuffer;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-08-13
 * Time : 17:25
 * Description:
 * Modified By:
 * version : v1.0
 */
public class BufferDemo {
    public static void main(String[] args) {
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        allocate.put("hello".getBytes());
        allocate.rewind();
        System.out.println(allocate.toString());
    }
}
