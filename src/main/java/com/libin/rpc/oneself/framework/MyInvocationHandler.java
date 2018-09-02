package com.libin.rpc.oneself.framework;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * Copyright (c) 2018/09/02. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 * <p>
 * Purpose : 通过实现InvocationHandler接口的invoke来完成远程RPC调用。
 */
class MyInvocationHandler implements InvocationHandler { //定义InvocationHandler接口的子类，以完成代理的具体操作。
    private Object obj; //真实主题
    private String host;
    private int port;

    MyInvocationHandler(String host,int port){
        this.host=host;
        this.port=port;
    }

    public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
        Socket socket = new Socket(host, port);
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            try {
                output.writeUTF(method.getName());
                output.writeObject(arguments);
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                try {
                    Object result = input.readObject();
                    if (result instanceof Throwable) {
                        throw (Throwable) result;
                    }
                    return result;
                } finally {
                    input.close();
                }
            } finally {
                output.close();
            }
        } finally {
            socket.close();
        }
    }

    @Deprecated
    Object bind(Object obj) { //绑定真实操作主题
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this); //取得代理对象
    }
}
