package cn.nickdlk;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author nickdlk
 */
public class AgentConfig {
    private static AgentConfig instance;
    private String agentArgs;
    private HashMap<String, List<String>> configMap = new HashMap<>();

    private AgentConfig(String agentArgs) {
        this.agentArgs = agentArgs;
        if (agentArgs != null && !agentArgs.isEmpty()) {
            String[] split = agentArgs.split(";");
            for (String arg : split) {
                String[] sp = arg.split("=");
                if (sp.length > 0) {
                    this.configMap.put(sp[0], Arrays.asList(sp[1].split(",")));
                }
            }
        }
        System.out.println(configMap.toString());
    }

    public static void initialize(String agentArgs) {
        instance = new AgentConfig(agentArgs);
    }

    public static String getAgentArgs() {
        return instance.agentArgs;
    }

    public static HashMap<String, List<String>> getConfigMaps() {
        return instance.configMap;
    }

    public static List<String> getValue(String key) {
        return instance.configMap.getOrDefault(key, null);
    }

    public HashMap<String, List<String>> getConfigMap() {
        return configMap;
    }
}

