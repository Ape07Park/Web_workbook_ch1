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

@WebServlet(name = "todoSelectOneController", value = "/todo/view")
@Log4j2
public class TodoSelectOneController extends HttpServlet {

    private final TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            Long id = Long.parseLong(request.getParameter("tno"));

            TodoDto todo = todoService.get(id);

            request.setAttribute("todo", todo);

            request.getRequestDispatcher("/WEB-INF/todo/view.jsp").forward(request, response);

        } catch (
                Exception e) {
            log.error("Todo select error", e);
            throw new ServletException(e);
        }

    }
}
