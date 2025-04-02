package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.TodoDto;

import java.time.LocalDate;

@Controller
@Log4j2
public class SampleController {

    //  메소드 이름을 따라 view 이름도 따라감
    @GetMapping("/hello") // get 방식의 요청을 처리
    public void hello() {
        log.info("Hello, Spring---------!");
    }

    // @RequestParam으로 명시 해야 함
    @GetMapping("/ex1")
    public void ex1(@RequestParam(name = "name") String name, @RequestParam(name = "age") int age) {
        log.info("ex1........");
        log.info("name: " + name);
        log.info("age: " + age);
    }

    @GetMapping("/ex2")
    // @RequestParam: 요청의 파라미터(쿼리 파라미터)를 받는다. name 속성은 파라미터의 이름이며 defaultValue 속성은 파라미터의 기본값을 설정하고
    // required 속성은 반드시 파라미터의 값이 있어야 하는지 여부이다.
    // 파라미터의 이름과 변수의 이름이 같다면 속성값을 설정하지 않아도 자동으로 파라미터의 이름에 맞게 값이 들어간다.
    public void ex2(@RequestParam(name = "name", defaultValue = "AAA", required = false) String name,
                    @RequestParam(name = "age", defaultValue = "20", required = false) int age) {
        log.info("ex2........");
        log.info("name: " + name);
        log.info("age: " + age);
    }

    @GetMapping("/ex3")
    public void ex3(@RequestParam(name = "dueDate") LocalDate dueDate) {
        log.info("ex3........");
        log.info("dueDate: " + dueDate);
    }

    @GetMapping("/ex4")
    public void ex4(Model model) { // Model을 통해 뷰 단에 내가 원하는 데이터를 실어 보낼 수 있다. 데이터 형식은 이름, 값 의 형태이다.

        log.info("-----------------");
        // 뷰 단에 전달할 것을 모델에 실음. 서블릿의 request.setAttribute()를 통해 데이터를 실은 것과 동일하다
        model.addAttribute("message", "Hello, world!");
    }

    @GetMapping("/ex4-1")
    public void ex4Extra(@ModelAttribute("dto") TodoDto todoDto, Model model) {
        // 사용자 정의 객체가 파라미터이고 그 파라미터가 getter/setter를 지니고 있으면 자동으로 뷰 단까지 데이터를 전달한다.
        // 즉 todoDto가 뷰 단으로 자동으로 전달된다.
        // @ModelAttribute("dto"): 자동으로 전달되는 todoDto의 이름을 dto로 변경한다.

        log.info(todoDto);
    }

    @GetMapping("/ex5")
    public String ex5(RedirectAttributes redirectAttributes) { // PRG(POST, Redirect, GET)

        // redirect할 때 URL에 쿼리 스트링에 실어보낼 것의 key, value
        redirectAttributes.addAttribute("name", "ABC");

        // JSP에서 일회성으로 사용할 것
        redirectAttributes.addFlashAttribute("result", "Success");

        // redirect: URL
        return "redirect:/ex6";
    }

    //  메소드 이름을 따라 view 이름도 따라감 따라서 /ex6를 주소창에 입력 시 ex6.jsp 파일을 찾음. 없으면 404
    @GetMapping("/ex6")
    public void ex6() {}

    @GetMapping("/ex7")
    public void ex7(@RequestParam("p1") String p1, @RequestParam("p2") int p2) {
        log.info("p1........ + p1");
        log.info("p2........ + p2");
    }

}
