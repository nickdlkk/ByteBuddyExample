package cn.nickdlk;

import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.concurrent.Callable;

/**
 * @Author nickdlk
 */
public class LicenseInterceptorInterceptor {
    @RuntimeType
    public static Object intercept(@Origin Method method, @AllArguments Object[] args,
                                   @SuperCall Callable<?> callable) throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("License方法开始执行...");
        Object resObj = null;
        try {

            if (method.getName().equals("preHandle")) {
                return true; // 改变拦截器逻辑,返回true放行
            }
            resObj = callable.call();
            return resObj;
        } finally {
            System.out.println("License方法类名：" + method.getDeclaringClass().getName());
            System.out.println("License方法名称：" + method.getName());
            System.out.println("License入参个数：" + method.getParameterCount());
            final Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                System.out.println(
                        "License入参名称：" + parameters[i].getName() + " 入参类型：" + method.getParameterTypes()[i].getTypeName() + " " + "参数值：" + " " + args[i]);
            }
            System.out.println("License出参类型：" + method.getReturnType().getName());
            System.out.println("License出参结果：" + resObj);
            System.out.println("License方法耗时：" + (System.currentTimeMillis() - start) + "ms \n");
        }
    }
}
