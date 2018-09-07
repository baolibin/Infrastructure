package com.libin.socket;

/**
 * Copyright (c) 2018/09/02. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose :
 */
public class SocketClient {
    public static void main(String[] args) throws Exception {
        SocketClientBase socketClientBase = new SocketClientBase(9999);
        socketClientBase.getMessage();
    }
}
