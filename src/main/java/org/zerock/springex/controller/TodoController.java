package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @RequestMapping: 경로를 지정해 지정한 경로에 오는 요청을 처리할 수 있게 하는 역할을 한다.
 * 클래스 단위: 상위 경로를 지정하고 지정한 경로로 오는 요청을 처리한다. 클래스 단위로 하면 클래스의 하위에 해당하는 메소드들의 경로는 자동으로 "/상위 경로/하위 경로"의 형태가 된다.
 * 클래스 단위에서 사용할 땐 method 속성값을 사용하지 않는다. 이유는 http 메소드를 가리지 않고 상위 경로로 오는 요청은 다 받아야 하기 때문이다.
 *
 * 메소드 단위: 지정한 경로로 오는 요청을 처리. value(경로), method(http 메소드)를 속성값으로 가진다. method 속성으로 어떤 방식으로 처리할 지 지정한다.
 */

@Controller
@RequestMapping("/todo") // 클래스 단위로서 상위 경로를 지정하고 지정한 경로로 오는 요청을 처리한다.
@Log4j2
public class TodoController {

    @RequestMapping("/list") // 메소드 단위로서 지정한 경로로 오는 요청을 처리. value(경로), method(http 메소드)를 속성값으로 가진다
    public void list() {
        log.info("todo list.........");
    }

    @RequestMapping(value="/write", method = RequestMethod.GET) // get 방식으로 받기
    public void write() {
        log.info("todo write.........");
    }

    @PostMapping("write") // 지정한 경로로 오는 요청을 post 방식으로 처리한다
    public void writePost() {
        log.info("todo write POST.........");
    }

}
