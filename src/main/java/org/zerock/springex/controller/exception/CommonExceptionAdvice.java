package org.zerock.springex.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.lang.reflect.Array;
import java.util.Arrays;

@Log4j2
@ControllerAdvice // 컨트롤러에서 발생한 예외를 각 상황에 맞게 처리하는 역할을 함
// ControllerAdvice에 예외처리를 맡김으로써 다른 곳에서는 예외처리 신경 안써도 됨. 즉 역할 분담 및 전역 예외처리기
public class CommonExceptionAdvice {

    @ResponseBody // return 값을 그대로 HTTP 바디에 실어서 보냄.
    @ExceptionHandler(NumberFormatException.class) // NumberFormatException 타입의 예외를 파라미터로 받아 처리 가능. 즉 NumberFormatException 예외처리
    public String handleNumberFormatException(NumberFormatException e) {

        log.error("-----------------");
        log.error(e.getMessage());

        return "NumberFormatException occurred: " + e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(Exception.class) // Exception 타입의 예외를 파라미터로 받아 처리 가능. ��� Exception(상위 클래스) 예외��리
    public String handleException(Exception e) {

        log.error("----------------");
        log.error(e.getMessage());

        // StringBuffer: 수정가능한 String
        StringBuffer buffer = new StringBuffer("<ul>");

        buffer.append("<li>" + e.getMessage() + "</li>");

        Arrays.stream(e.getStackTrace()).forEach(stackTraceElement -> {
            buffer.append("<li>" + stackTraceElement + "</li>");
        });

        buffer.append("</ul>");

        return buffer.toString();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(){

        return "custom404";
    }

}
