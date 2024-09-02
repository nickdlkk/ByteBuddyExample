package cn.nickdlk.track;

import java.util.LinkedList;

/**
 * @Author nickdlk
 */
public class TrackLinkList {
    private static final ThreadLocal<LinkedList<LinkNode>> track = new ThreadLocal<>();

    public static void add(String className, String methodName, String status) {
        LinkedList<LinkNode> list = track.get();
        if (list == null) {
            list = new LinkedList<>();
            track.set(list);
        }
        LinkNode linkNode = new LinkNode(className, methodName, status);
        list.add(linkNode);
    }

    public static void PrintLinkList() {
        LinkedList<LinkNode> list = track.get();
        if (list == null) {
            return;
        }
        String print = "";
        for (int i = 0; i < list.size(); i++) {
            print += "[" + i + "]" + list.get(i).ClassName + "." + list.get(i).MethodName + "(" + list.get(i).Status + ")  " + "\n";
        }
        System.out.println(print);
    }

    static class LinkNode {
        String ClassName;
        String MethodName;
        String Status;

        public LinkNode(String className, String methodName, String status) {
            ClassName = className;
            MethodName = methodName;
            Status = status;
        }
    }

}
