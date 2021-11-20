package com.baich.rpc.demo02;

import com.baich.rpc.common.User;
import com.baich.rpc.common.UserService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-09-08
 * Time : 15:46
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Stub {
    public static UserService getStub() {
        InvocationHandler handler = new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket("localhost", 8080);
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeInt(23);
                int id = dis.readInt();
                String name = dis.readUTF();
                User user = new User(id, name);
                dos.close();
                socket.close();
                return user;
            }
        };
        Object o = Proxy.newProxyInstance(UserService.class.getClassLoader(), new Class[]{UserService.class}, handler);
        System.out.println(o.getClass().getName());
        System.out.println(o.getClass().getInterfaces()[0]);
        return (UserService) o;
    }
}
