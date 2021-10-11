package com.baich.bigdata.rpc.demo01;

import com.baich.bigdata.rpc.common.UserService;
import com.baich.bigdata.rpc.common.User;
import com.baich.bigdata.rpc.common.UserServiceImpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-09-07
 * Time : 17:28
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Server {
    private static boolean running = true;

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (running) {
            Socket socket = serverSocket.accept();
            process(socket);
            socket.close();
        }
        serverSocket.close();

    }

    private static void process(Socket socket) throws Exception {
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        int id = dis.readInt();
        UserService service = new UserServiceImpl();
        User user = service.findUserById(id);
        dos.writeInt(user.getId());
        dos.writeUTF(user.getName());
        dos.flush();
    }
}
