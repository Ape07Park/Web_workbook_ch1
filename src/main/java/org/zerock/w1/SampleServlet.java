package org.zerock.w1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(urlPatterns = "/sample") // 사용 이유: 특정 브라우저 주소에 해당 서블릿 연결
public class SampleServlet extends HttpServlet {
    
    // url 호출 시 작동한다(한번만 작동)
    @Override
    public void init() throws ServletException {
        System.out.println("SampleServlet init...............");
    }
    
    // 계속 작동한다(재사용)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet.............");
    }

    // 계속 작동한다(재사용)
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        System.out.println("doPost..............");

        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");

        System.out.printf(" num1: %s", num1);
        System.out.printf(" num2: %s", num2);

        
    }
    // 서버 종료 시 호출한다 (한번만 작동)
    public void destroy() {
        System.out.println("destroy.............");
    }
}