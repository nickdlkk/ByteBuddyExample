package cn.nickdlk.plugin.impl.linkLog;

import cn.nickdlk.track.Span;
import cn.nickdlk.track.TrackContext;
import cn.nickdlk.track.TrackLinkList;
import cn.nickdlk.track.TrackManager;
import net.bytebuddy.asm.Advice;

import java.util.UUID;


public class LinkLogAdvice {

    @Advice.OnMethodEnter()
    public static void enter(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        Span currentSpan = TrackManager.getCurrentSpan();
        if (null == currentSpan) {
            String linkId = UUID.randomUUID().toString();
            TrackContext.setLinkId(linkId);
        }
        TrackManager.createEntrySpan();
        TrackLinkList.add(className, methodName, "entry");
    }

    @Advice.OnMethodExit()
    public static void exit(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        TrackManager.getExitSpan();

        TrackLinkList.add(className, methodName, "exit");
        if (TrackManager.isExistSpan()) {
            TrackLinkList.PrintLinkList();
        }
    }

}
