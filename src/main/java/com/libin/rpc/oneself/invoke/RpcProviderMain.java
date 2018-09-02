package com.libin.rpc.oneself.invoke;

import com.libin.rpc.oneself.framework.ProviderReflect;
import com.libin.rpc.oneself.service.HelloService;
import com.libin.rpc.oneself.service.HelloServiceImpl;

/**
 *  发布服务，接收调用。
 */
public class RpcProviderMain {

    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        ProviderReflect.provider(service, 9999);
    }
}
