package cn.nickdlk;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @Author nickdlk
 */
public class SpringAgent {
    // 这种方式这个不会执行
    public static void premain(String agentArgs) {
        System.out.println("我是一个参数的 Java Agent premain");
    }

    // 入口
    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("MyAgentMain start..");
        instrumentation.addTransformer(new DefineTransformer(), true);
    }

    // 2个参数的优先级大于1个参数的 这个不会执行
    public static void agentmain(String agentArgs) {
        System.out.println("我是一个参数的 Java Agent agentmain");
    }

    static class DefineTransformer implements ClassFileTransformer {
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            System.out.println("premain load Class:" + className);
            return classfileBuffer;
        }
    }
}
