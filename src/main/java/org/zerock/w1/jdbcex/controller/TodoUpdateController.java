package org.zerock.w1.jdbcex.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.zerock.w1.jdbcex.domain.TodoVo;
import org.zerock.w1.jdbcex.dto.TodoDto;
import org.zerock.w1.jdbcex.service.TodoService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "todoUpdateController", value = "/todo/update")
@Log4j2
public class TodoUpdateController extends HttpServlet {

    private final TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            Long tno = Long.parseLong(request.getParameter("tno"));

            TodoDto todo = todoService.get(tno);

            request.setAttribute("todo", todo);

            request.getRequestDispatcher("/WEB-INF/todo/update.jsp").forward(request, response);

        } catch (
                Exception e) {
            log.error("Todo select error", e);
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       String finishedStr = request.getParameter("finished");

       // 요청 받은 내용
        TodoDto todoDto = TodoDto.builder()
                .tno(Long.parseLong(request.getParameter("tno")))
                .title(request.getParameter("title"))
                .dueDate(LocalDate.parse(request.getParameter("dueDate"), DATEFORMATTER))
                .finished(finishedStr != null && finishedStr.equals("on"))
                .build();

        log.info("todo/update POST.....");
        log.info(todoDto);

        try{
            todoService.update(todoDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/todo/list");

    }



}
