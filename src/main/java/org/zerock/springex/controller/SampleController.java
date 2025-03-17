package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello") // get 방식의 요청을 처리
    public void hello() {
        log.info("Hello, Spring---------!");
    }

    // TODO 파라미터 자동 수집 및 변환 작업 중
    @GetMapping("/ex1")
    public void ex1( String name, int age) {
        log.info("ex1........");
        log.info("name: " + name);
        log.info("age: " + age);
    }
}
