
## 动态代理
静态代理每一个代理类只能为一个接口服务，这样会产生过多的代理。通过一个代理类完成全部的代理功能，这时就需要使用动态代理。
在Java中实现动态代理：需要 java.lang.reflect.InvocationHandler 接口  和 java.lang.reflect.Proxy 类支持。
