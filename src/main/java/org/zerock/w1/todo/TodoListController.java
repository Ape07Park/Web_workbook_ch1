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


@WebServlet(urlPatterns = "/todo/list")
public class TodoListController extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,IOException {

        System.out.println("/todo/list...............");
        
        List<TodoDto> dtoList = TodoService.INSTANCE.getTodoList();
        
        // 요청 객체에 dtoList 담아 보내기
        request.setAttribute("list", dtoList);

        // 요청과 응답을 보낼 주소 설정
        request.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(request, response);
    }
}