package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello") // get 방식의 요청을 처리
    public void hello() {
        log.info("Hello, Spring---------!");
    }

    // @RequestParam으로 명시 해야 함
    @GetMapping("/ex1")
    public void ex1( String name, int age) {
        log.info("ex1........");
        log.info("name: " + name);
        log.info("age: " + age);
    }

    @GetMapping("/ex2")
    // @RequestParam: 요청의 파라미터(쿼리 파라미터)를 받는다. name 속성은 파라미터의 이름이며 defaultValue 속성은 파라미터의 기본값을 설정한다.
    // 파라미터의 이름과 변수의 이름이 같다면 속성값을 설정하지 않아도 자동으로 파라미터의 이름에 맞게 값이 들어간다.
    public void ex2(@RequestParam(name = "name", defaultValue = "AAA") String name,
                    @RequestParam(name = "age", defaultValue = "20") int age) {
        log.info("ex2........");
        log.info("name: " + name);
        log.info("age: " + age);
    }

    @GetMapping("/ex3")
    public void ex3(@RequestParam(name = "dueDate") LocalDate dueDate) { 
        log.info("ex3........");
        log.info("dueDate: " + dueDate);
    }
}
