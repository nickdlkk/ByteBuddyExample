package cn.nickdlk.patchspring.controller;

import cn.nickdlk.patchspring.dao.User;
import cn.nickdlk.patchspring.service.IDemoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author nickdlk
 */
@RestController
public class testController {
    @Autowired
    IDemoService demoService;

    @GetMapping(value = "/sayHello")
    public User sayHello(String name) {
        final User user = demoService.sayHello(name);
        return user;
    }

    @GetMapping("/test")
    public String hello(HttpServletRequest request, HttpServletResponse response, String name) {
        // .enter() 方法体的内容全部放在此处
        String a = "hello " + name;
        // .exit() 方法体的内容全部放在此处
        return a;
    }

//    @Async
//    @Scheduled(cron = " 0/10 * * * * ? ")
//    @GetMapping("/cron")
//    public String assetCount() {
//        System.out.println("定时任务");
//        return "Scheduled";
//    }
}
