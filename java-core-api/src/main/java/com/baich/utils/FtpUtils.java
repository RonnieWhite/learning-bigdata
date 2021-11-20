package com.baich.utils;


import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-27
 * Time : 20:36
 * Description:
 * Modified By:
 * version : v1.0
 */
public class FtpUtils {
    public static void putFile() throws IOException, FtpProtocolException {
        String host = "vm01", user = "test", password = "test", targetPath = "/home/test/", localPath = "D:/data/", fileName = "data.txt";
        int port = 21;
        FtpClient ftpClient = FtpClient.create();
        InetSocketAddress address = new InetSocketAddress(host, port);
        ftpClient.connect(address);
        ftpClient.login(user, password.toCharArray());
        ftpClient.enablePassiveMode(true);
        InputStream inputStream = new FileInputStream(new File(localPath.concat(fileName)));
        ftpClient.putFile(targetPath.concat(fileName), inputStream,true);
    }
}
