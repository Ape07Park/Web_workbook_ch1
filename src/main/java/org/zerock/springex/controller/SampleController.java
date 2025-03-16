package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello") // get 방식의 요청을 처리
    public void hello() {
        log.info("Hello, Spring---------!");
    }
}
