package cn.nickdlk;

import jakarta.servlet.http.HttpServletResponse;
import net.bytebuddy.asm.Advice;

import java.util.UUID;

/**
 * @Author nickdlk
 */
public class LogAdvice {
    // 方法执行前
    @Advice.OnMethodEnter(inline = true)
    public static void enter(@Advice.This Object obj, @Advice.AllArguments Object[] allArguments,
                             @Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        System.out.println("MyAdvice: " + className + "." + methodName);
    }

    // 方法执行后, inline = true 必须使用默认, 否则会出错
    @Advice.OnMethodExit
    public static void exit(@Advice.This Object obj, @Advice.AllArguments Object[] allArguments,
                            @Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        for (Object argument : allArguments) {
            if (argument instanceof HttpServletResponse) {
                HttpServletResponse response = (HttpServletResponse) argument;
                response.setHeader("t_id", UUID.randomUUID().toString());
                response.setHeader("t_c_n", className);
                response.setHeader("t_m_n", methodName);
            }
        }
    }
}
