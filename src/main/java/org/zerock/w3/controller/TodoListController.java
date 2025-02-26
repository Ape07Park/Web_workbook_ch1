package org.zerock.w3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.zerock.w3.dto.TodoDto;
import org.zerock.w3.service.TodoService;


import java.io.IOException;
import java.util.List;

@WebServlet(name = "todoListController", value = "/todo/list")
@Log4j2
public class TodoListController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<TodoDto> dtoList = todoService.listAll();
            request.setAttribute("todoList", dtoList);
            request.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(request, response);

        }  catch (Exception e) {
            log.error("TodoListController doGet error : ", e);
            throw new ServletException("list error");
        }
    }

}
