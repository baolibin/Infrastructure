package com.libin.rpc.oneself.invoke;

import com.libin.rpc.oneself.framework.ConsumerProxy;
import com.libin.rpc.oneself.service.HelloService;

/**
 *  服务的调用客户端。
 */
public class RpcConsumerMain {

    public static void main(String[] args) throws Exception {
        HelloService service = ConsumerProxy.consume(HelloService.class, "localhost", 9999);
        for (int i = 0; i < 5; i++) {
            String hello = service.sayHello("hadoop_" + i);
            System.out.println(hello);
            Thread.sleep(1000);
        }
    }
}
