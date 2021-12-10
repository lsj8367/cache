package io.github.lsj8367.web;

import io.github.lsj8367.service.ExService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExController {

    private final ExService exService;

    public void run() {
        log.info("User1" + exService.getuser(1L));
        log.info("User1" + exService.getuser(1L));
        log.info("User1" + exService.getuser(1L));
        log.info("User2" + exService.getuser(2L));
        log.info("User2" + exService.getuser(2L));
        log.info("User2" + exService.getuser(2L));
    }
}
