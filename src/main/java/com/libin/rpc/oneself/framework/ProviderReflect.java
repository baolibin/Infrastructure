package com.libin.rpc.oneself.framework;

import org.apache.commons.lang3.reflect.MethodUtils;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通过Java对象输入流ObjectInputStream从Socket中按照ConsumerProxy的写入顺序逐一获取调用方法名称及方法参数，
 * 通过MethodUtils.invokeExactMethod对服务实现类发起反射调用，将调用结果写入Socket返回给调用方。
 */
public class ProviderReflect {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    /**
     * 服务的发布
     * @param service
     * @param port
     * @throws Exception
     */
    public static void provider(final Object service, int port) throws Exception {
        if (service == null || port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Illegal param.");
        }
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            final Socket socket = serverSocket.accept();
            executorService.execute(new Runnable() {
                public void run() {
                    try {
                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                        String methodName = objectInputStream.readUTF();
                        Object[] arguments = (Object[]) objectInputStream.readObject();

                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        //对服务实现类发起反射调用，将调用结果写入Socket返回给调用方。
                        Object result = MethodUtils.invokeExactMethod(service, methodName, arguments);
                        output.writeObject(result);

                        output.close();
                        objectInputStream.close();
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
