package cn.nickdlk.plugin;


import cn.nickdlk.plugin.impl.jvm.JvmPlugin;
import cn.nickdlk.plugin.impl.link.LinkPlugin;
import cn.nickdlk.plugin.impl.linkLog.LinkLogPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * 博客：http://itstack.org
 * 论坛：http://bugstack.cn
 * 公众号：bugstack虫洞栈  ｛获取学习源码｝
 * create by fuzhengwei on 2019
 */
public class PluginFactory {

    public static List<IPlugin> pluginGroup = new ArrayList<>();

    static {
        //链路监控
//        pluginGroup.add(new LinkPlugin());
//        pluginGroup.add(new LinkLogPlugin());
        //Jvm监控
//        pluginGroup.add(new JvmPlugin());
    }

    public static void init(List<String> plugins) {
        for (String plugin : plugins) {
            switch (plugin) {
                case "link":
                    pluginGroup.add(new LinkPlugin());
                    break;
                case "linkLog":
                    pluginGroup.add(new LinkLogPlugin());
                    break;
                case "jvm":
                    pluginGroup.add(new JvmPlugin());
                    break;
                default:
                    System.out.println("插件不存在：" + plugin);
            }
        }
    }

}
