package org.zerock.w1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 어노테이션은 코드에 대한 추가 설정이나 처리 위해 사용
// urlPatterns로 요청 경로 지정
@WebServlet(urlPatterns = "/calc/makeResult") // 사용 이유: 특정 브라우저 주소에 해당 서블릿 연결
public class CalcController extends HttpServlet {
    
    @Override
    // doGet: 브라우저 주소 직접 변경해서 접근
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        System.out.println("CalcController.. doPost");

        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");

        System.out.printf(" num1: %s", num1);
        System.out.printf(" num2: %s", num2);


    }

    public void destroy() {
    }
}