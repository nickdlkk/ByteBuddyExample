import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import com.taobao.arthas.common.AnsiLog;
import com.taobao.arthas.common.JavaVersionUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @link https://github.com/alibaba/arthas/blob/master/core/src/main/java/com/taobao/arthas/core/Arthas.java
 */
public class attach {
    public static void main(String[] args) throws Exception {
        //        String targetPid = args[0];
        String targetPid = "";
        String targetDisplayName = "cn.nickdlk.springDemo.SpringDemoApplication";
        String agentPath = "D:\\demoJava\\ByteBuddyExample\\attach-spring-bytebuddy\\target\\attach-spring-bytebuddy-1.0-SNAPSHOT.jar";
        VirtualMachineDescriptor virtualMachineDescriptor = null;
        for (VirtualMachineDescriptor descriptor : VirtualMachine.list()) {
//            System.out.println(descriptor.displayName());
            String pid = descriptor.id();
            if (pid.equals(targetPid)) {
                virtualMachineDescriptor = descriptor;
                break;
            }
            if (descriptor.displayName().equals(targetDisplayName)) {
                virtualMachineDescriptor = descriptor;
                targetPid = pid;
                break;
            }
        }
        VirtualMachine virtualMachine = null;
        try {
            if (null == virtualMachineDescriptor) { // 使用 attach(String pid) 这种方式
                virtualMachine = VirtualMachine.attach("" + targetPid);
            } else {
                virtualMachine = VirtualMachine.attach(virtualMachineDescriptor);
            }
            Properties targetSystemProperties = virtualMachine.getSystemProperties();
            String targetJavaVersion = JavaVersionUtils.javaVersionStr(targetSystemProperties);
            String currentJavaVersion = JavaVersionUtils.javaVersionStr();
            if (targetJavaVersion != null && currentJavaVersion != null) {
                if (!targetJavaVersion.equals(currentJavaVersion)) {
                    AnsiLog.warn("Current VM java version: {} do not match target VM java version: {}, attach may fail.",
                            currentJavaVersion, targetJavaVersion);
                    AnsiLog.warn("Target VM JAVA_HOME is {}, arthas-boot JAVA_HOME is {}, try to set the same JAVA_HOME.",
                            targetSystemProperties.getProperty("java.home"), System.getProperty("java.home"));
                }
            }

            try {
                virtualMachine.loadAgent(agentPath);
            } catch (IOException e) {
                if (e.getMessage() != null && e.getMessage().contains("Non-numeric value found")) {
                    AnsiLog.warn(e);
                    AnsiLog.warn("It seems to use the lower version of JDK to attach the higher version of JDK.");
                    AnsiLog.warn(
                            "This error message can be ignored, the attach may have been successful, and it will still try to connect.");
                } else {
                    throw e;
                }
            } catch (com.sun.tools.attach.AgentLoadException ex) {
                if ("0".equals(ex.getMessage())) {
                    // https://stackoverflow.com/a/54454418
                    AnsiLog.warn(ex);
                    AnsiLog.warn("It seems to use the higher version of JDK to attach the lower version of JDK.");
                    AnsiLog.warn(
                            "This error message can be ignored, the attach may have been successful, and it will still try to connect.");
                } else {
                    throw ex;
                }
            }
        } finally {
            if (null != virtualMachine) {
                virtualMachine.detach();
            }
        }
    }

}
