package cn.nickdlk;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.reflect.Method;

/**
 * @Author nickdlk
 */
public class AnnotatedInterceptor {

    @RuntimeType
    public static Object intercept(@Origin Method method) {
        System.out.println("跳过定时任务:" + method.getDeclaringClass().getName());
        try {
            return null;
        } catch (Exception e) {

        }
        return null;
    }
}
