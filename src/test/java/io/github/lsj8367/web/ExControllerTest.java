package io.github.lsj8367.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExControllerTest {

    @Autowired
    private ExController exController;

    @Test
    void test() {
        exController.run();
    }

}