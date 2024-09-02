package cn.nickdlk;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;
import org.springframework.scheduling.annotation.Scheduled;

import java.lang.instrument.Instrumentation;


/**
 * @Author nickdlk
 */
public class SpringAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Spring Agent Loaded");
        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule, protectionDomain) -> {
            // 演示1 跳过拦截器
            if (typeDescription.getName().equals("cn.nickdlk.patchspring.interceptor.LicenseInterceptor")) {
                System.out.println("hit:" + typeDescription.getName());
                builder = builder.method(ElementMatchers.isPublic().and(ElementMatchers.named("preHandle")))
                        .intercept(MethodDelegation.to(LicenseInterceptorInterceptor.class));
            }

            // 演示2 跳过定时任务注解
            builder = builder.method(ElementMatchers.isAnnotatedWith(Scheduled.class)) // 指定注解类,需要引入Spring的包来使用,可以通过其他方式指定
                    .intercept(MethodDelegation.to(AnnotatedInterceptor.class));

            // 演示3 对Controller增加额外返回
            if (typeDescription.getName().startsWith("cn.nickdlk.patchspring.controller")) {
                builder = builder.visit(Advice
                        .to(LogAdvice.class)
                        .on(ElementMatchers.isMethod()
                                .and(ElementMatchers.not(ElementMatchers.isConstructor()))
                                .and(ElementMatchers.not(ElementMatchers.isStatic()))
                                .and(ElementMatchers.not(ElementMatchers.isSetter()))
                                .and(ElementMatchers.not(ElementMatchers.isGetter()))
                                .and(ElementMatchers.not(ElementMatchers.isToString()))
                                .and(ElementMatchers.any())
                                .and(ElementMatchers.not(ElementMatchers.nameStartsWith("main")))));
            }
            return builder;
        };

        AgentBuilder.Listener listener = new AgentBuilder.Listener() {


            @Override
            public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {

            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {

            }

            @Override
            public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }
        };

        new AgentBuilder
                .Default()
                .type(ElementMatchers.isPublic())
                .transform(transformer)
                .with(listener)
                .installOn(inst);

    }
}
