# ByteBuddy

本项目用于演示ByteBuddy的各种使用方法，主要是JavaAgent的方式。

# JDK
JDK17

# Example
## Spring Patch JavaAgent

spring-demo SpringBoot工程，用于演示注入

patch-spring-bytebuddy 生成agent包，对Spring工程进行修改

详情见：[patch-spring-bytebuddy/README.md](patch-spring-bytebuddy/README.md)

## Spring Patch Attach

使用 Attach API 的方式，不需要重启\指定VM Options就能侵入已经运行的java进程。