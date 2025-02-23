package org.zerock.w1.jdbcex.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.zerock.w1.jdbcex.dto.TodoDto;
import org.zerock.w1.jdbcex.service.TodoService;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "todoDeleteController", value = "/todo/delete")
@Log4j2
public class TodoDeleteController extends HttpServlet {
    private final TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long tno = Long.parseLong(request.getParameter("tno"));
        log.info("todo/delete POST.....");

        try {
            todoService.remove(tno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/todo/list");

    }

}
