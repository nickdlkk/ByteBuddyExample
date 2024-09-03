# attach方式注入

先打包成jar包，再用[test](src/test/java/attach.java)里的方法向Spring演示项目注入。（Spring项目先启动，不需要配置VM Options）

# 入口

使用attach方式的入口是`agentmain`

# 打包配置

注意打包配置里使用的是`Agent-Class`

# 参考

https://www.sitepoint.com/fixing-bugs-in-running-java-code-with-dynamic-attach/

[Arthas](https://github.com/alibaba/arthas/blob/master/core/src/main/java/com/taobao/arthas/core/Arthas.java)

[JavaAgent的使用总结_virtualmachine.loadagent-CSDN博客](https://blog.csdn.net/keeppractice/article/details/124204861)

