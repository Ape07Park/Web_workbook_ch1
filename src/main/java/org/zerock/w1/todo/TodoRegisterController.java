package org.zerock.w1.todo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(urlPatterns = "/todo/register")
public class TodoRegisterController extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,IOException {

        System.out.println("/todo/register...............");

        // RequestDispatcher: 클라이언트로부터 요청을 받아서 특정 파일로 보내는 객체
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/todo/register.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,IOException {

        System.out.println("입력을 처리하고 목록으로 이동");

        // PRG 패턴 적용해 입력 처리 후 리스트 페이지로 Redirect
        response.sendRedirect("/todo/list");

    }
}