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

@WebServlet(name = "todoUpdateController", value = "/todo/update")
@Log4j2
public class TodoUpdateController extends HttpServlet {

    private final TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            Long id = Long.parseLong(request.getParameter("tno"));

            TodoDto todo = todoService.get(id);

            request.setAttribute("todo", todo);

            request.getRequestDispatcher("/WEB-INF/todo/update.jsp").forward(request, response);

        } catch (
                Exception e) {
            log.error("Todo select error", e);
            throw new ServletException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }


}
