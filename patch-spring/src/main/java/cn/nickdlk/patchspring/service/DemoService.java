package cn.nickdlk.patchspring.service;

import cn.nickdlk.patchspring.dao.User;
import org.springframework.stereotype.Service;

/**
 * @Author nickdlk
 */
@Service
public class DemoService implements IDemoService {
    public User sayHello(String name) {
        final User user = new User();
        user.setName(name);
        return user;
    }
}
