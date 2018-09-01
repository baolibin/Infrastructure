package com.libin.base;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Copyright (c) 2018/09/01. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose :
 */
public class SocketBase {
    public static void main(String[] args) throws Exception {
        UrlBase urlBase1 = new UrlBase("http", "java.sun.com", 80, "/index.html");
        // urlBase1.getPage();

        UrlBase urlBase2 = new UrlBase("https://www.baidu.com");
        // urlBase2.getUrlConnection();

        SocketServerBase socketServerBase = new SocketServerBase(9999);
        socketServerBase.sendMessage(new Socket());
    }
}

/**
 * 在Java中使用Socket(套接字)完成TCP程序的开发，可以方便建立可靠性、双向性、持续性、点对点的通信连接。
 */
class SocketServerBase {
    private ServerSocket server;

    SocketServerBase(int port) throws Exception {
        server = new ServerSocket(port);
    }

    void sendMessage(Socket clientBase) throws Exception {
        System.out.println("服务端已经启动，等待客户端连接发送消息:");
        clientBase = server.accept();
        PrintStream stream = new PrintStream(clientBase.getOutputStream());
        stream.println("server sead message to client get connection!");
        stream.close();
        server.close();
    }
}

class SocketClientBase {
    private Socket client;

    SocketClientBase(int port) throws Exception {
        client = new Socket("localhost", port);
    }

    void getMessage() throws Exception {
        BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String line = buf.readLine();
        System.out.println(line);
        client.close();
        buf.close();
    }

    Socket getClientSocket(){
        return client;
    }
}

/**
 * URL(Uniform Resource Locator)统一资源定位符
 */
class UrlBase {
    private String ptotocol; //协议
    private String host;  // 主机
    private int port; // 端口
    private String file; // 资源文件

    UrlBase(String host) {
        this.host = host;
    }

    UrlBase(String ptotocol, String host, int port, String file) {
        this.ptotocol = ptotocol;
        this.host = host;
        this.port = port;
        this.file = file;
    }

    /**
     * 通过URL找到互联网上指定资源
     */
    void getPage() throws Exception {
        URL url = new URL(ptotocol, host, port, file);
        InputStream inputStream = url.openStream();
        Scanner scan = new Scanner(inputStream);
        scan.useDelimiter("\n");
        while (scan.hasNext()) {
            System.out.println(scan.next());
        }
    }

    /**
     * URLConnection是封装访问远程网络资源一般方法的类，可以建立与远程服务器的连接，检查远程资源的一些属性。
     */
    void getUrlConnection() throws Exception {
        URL url = new URL(host);
        URLConnection urlConnection = url.openConnection();
        System.out.println(urlConnection.getContentEncoding());
        System.out.println(urlConnection.getContentLength());
        System.out.println(urlConnection.getContentType());
    }
}
