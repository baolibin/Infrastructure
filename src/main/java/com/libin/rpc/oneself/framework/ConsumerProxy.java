package com.libin.rpc.oneself.framework;

import java.lang.reflect.Proxy;

/**
 *  通过实现服务接口的动态代理对象获得服务接口的动态实例Proxy.newProxyInstance。
 *  通过实现InvocationHandler接口的invoke来完成远程RPC调用。
 *  通过Java的输出流ObjectInputStream将调用接口的方法及参数写入Socket。
 */
public class ConsumerProxy {
    /**
     * 服务消费代理接口
     * @param interfaceClass
     * @param <T>
     */
    public static <T> T consume(final Class<T> interfaceClass, final String host, final int port) throws Exception {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new MyInvocationHandler(host,port));
    }
}
