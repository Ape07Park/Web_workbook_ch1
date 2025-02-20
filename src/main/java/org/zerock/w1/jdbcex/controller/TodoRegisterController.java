package org.zerock.w1.jdbcex.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.zerock.w1.jdbcex.dto.TodoDto;
import org.zerock.w1.jdbcex.service.TodoService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "todoRegisterController", value = "/todo/register")
@Log4j2
public class TodoRegisterController {

    private TodoService todoService = TodoService.INSTANCE;
    // db에 저장되어 있는 타임포맷을 특정 형태로 바꾸기 위해 선언
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info(".........../todo/register GET");

        request.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(request, response);
    }


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
