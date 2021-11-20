package com.baich.rpc.demo02;

import com.baich.rpc.common.User;
import com.baich.rpc.common.UserService;
import com.baich.rpc.common.UserServiceImpl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-09-08
 * Time : 14:52
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Server2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);

        boolean running = true;
        while (true) {
            Socket socket = serverSocket.accept();
            process(socket);
        }

    }

    private static void process(Socket socket) throws IOException {
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        int id = dis.readInt();
        UserService userService = new UserServiceImpl();
        User user = userService.findUserById(id);
        dos.writeInt(user.getId());
        dos.writeUTF(user.getName());
        dos.flush();

    }
}
