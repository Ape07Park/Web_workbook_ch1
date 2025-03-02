package org.zerock.w3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.zerock.w3.dto.TodoDto;
import org.zerock.w3.service.TodoService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "todoRegisterController", value = "/todo/register")
@Log4j2
public class TodoRegisterController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    // db에 저장되어 있는 타임포맷을 특정 형태로 바꾸기 위해 선언
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info(".........../todo/register GET");
        
        // 세션 객체 가져오기 
        HttpSession session = request.getSession();
        
        // 기존 JSESSIONID가 없는 새로운 사용자면(아예 세션 쿠키가 없는 상태)
        if (session.isNew()) { 
            log.info("JSESSIONID 쿠키가 새로 만들어진 사용자" );
            response.sendRedirect("/login");
            return;
        }
        
        // JSESSIONID는 있지만 해당 세션 저장소에 loginInfo라는 이름으로 저장된 객체가 없는 경우
//        (세션 쿠키가 있지만 우리 사이트와 관련된 세션 쿠키가 아닌 상태 혹은 우리 사이트엔 들어왔지만 로그인은 안한 상태)
        if(session.getAttribute("loginInfo") == null) {
            
            log.info("로그인한 정보가 없는 사용자");
            response.sendRedirect("/login");
            return;
        }
        
        // 정상적인 로그인 시 이동
        request.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info(".........../todo/register POST");

        TodoDto todoDto = TodoDto.builder()
                .title(request.getParameter("title"))
                .dueDate(LocalDate.parse(request.getParameter("dueDate"), DATEFORMATTER))
                .build();

        log.info(todoDto);

        try {
            todoService.register(todoDto);
        } catch (Exception e){
            e.printStackTrace();
        }
        // 성공 후 리스트로 이동
        response.sendRedirect("/todo/list");
   }

}
