package io.github.lsj8367.service;

import io.github.lsj8367.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ExService {

    @Cacheable("user")
    public User getuser(final Long id) {
        try {
            System.out.println("user를 db에서 찾는다");
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new User(id, "홍길동");
    }
}
