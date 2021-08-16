package com.baich.bigdata.javase.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-08-14
 * Time : 11:18
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Server {

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ServerSocketChannel channel;
    private Selector selector;
    private int capacity;

    public void run() throws IOException {
        init();
        while (true) {
            selector.select();
            Set<SelectionKey> keySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                // 监听链接事件
                if (selectionKey.isAcceptable()) {
                    handleAcceptable(selector, channel);
                } else if (selectionKey.isReadable()) {
                    handleReadable(selectionKey);
                }
            }
        }
    }

    /**
     * 初始化ServerSocketChannel和selector，并注册
     *
     * @throws IOException
     */
    private void init() throws IOException {
        // 获取通道
        channel = ServerSocketChannel.open();
        SocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
        // 绑定通道地址
        channel.bind(address);
        // 设置通道为非阻塞
        channel.configureBlocking(false);
        // 打开选择器
        selector = Selector.open();
        // 通道注册
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 处理链接事件
     *
     * @param selector 选择器
     * @param channel  通道
     * @throws IOException
     */
    private void handleAcceptable(Selector selector, ServerSocketChannel channel) throws IOException {
        SocketChannel socketChannel = channel.accept();
        socketChannel.configureBlocking(false);
        // 注册可读事件
        socketChannel.register(selector, SelectionKey.OP_READ);

    }

    /**
     * 处理可读事件
     *
     * @param selectionKey 选择键
     * @throws IOException
     */
    private void handleReadable(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(capacity);
        StringBuilder builder = new StringBuilder();
        while (socketChannel.read(readBuffer) > 0) {
            readBuffer.flip();
            builder.append(StandardCharsets.UTF_8.decode(readBuffer));
        }
        if (builder.length() > 0) {
            System.out.println(builder);
        }
        // 继续注册可读事件
//        socketChannel.register(selector, SelectionKey.OP_READ);
    }

}
