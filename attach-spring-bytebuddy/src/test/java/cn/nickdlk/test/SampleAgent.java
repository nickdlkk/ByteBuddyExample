package cn.nickdlk.test;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class SampleAgent {

    public static String argument;

    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("MyAgentMain start..");
        instrumentation.addTransformer(new DefineTransformer(), true);
        SampleAgent.argument = argument;
    }

    static class DefineTransformer implements ClassFileTransformer {
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            System.out.println("premain load Class:" + className);
            return classfileBuffer;
        }
    }
}
