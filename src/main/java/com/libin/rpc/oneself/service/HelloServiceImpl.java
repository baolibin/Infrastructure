package com.libin.rpc.oneself.service;

/**
 *  远程接口服务实现。
 */
public class HelloServiceImpl implements HelloService {

    public String sayHello(String content) {
        return "rpc hello," + content;
    }
}
