package org.zerock.w1.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.zerock.w1.todo.dto.TodoDto;
import org.zerock.w1.todo.dto.service.TodoService;

import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/todo/view")
public class TodoReadController extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,IOException {

        System.out.println("/todo/view...............");

        Long tno = Long.parseLong(request.getParameter("tno"));

        TodoDto todoDto = TodoService.INSTANCE.getTodoDetail(tno);
        
        // 요청 객체에 dtoList 담아 보내기
        request.setAttribute("todo", todoDto);

        // 요청과 응답을 보낼 주소 설정
        request.getRequestDispatcher("/WEB-INF/todo/view.jsp").forward(request, response);
    }
}