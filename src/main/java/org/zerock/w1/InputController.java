package org.zerock.w1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// 어노테이션은 코드에 대한 추가 설정이나 처리 위해 사용
// urlPatterns로 요청 경로 지정
@WebServlet(urlPatterns = "/calc/input") // 사용 이유: 특정 브라우저 주소에 해당 서블릿 연결
public class InputController extends HttpServlet {
    
    @Override
    // doGet: 브라우저 주소 직접 변경해서 접근
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        System.out.println("InputController.. doGet");
        /**
         * RequestDispatcher: 서블릿으로 온 요청을 다른 곳으로 전달
         */
        // 서블릿 요청 전달할 파일 지정
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/calc/input.jsp");
        
        // 서블릿의 요청과 응답을 내가 지정한 파일로 전달
        rd.forward(request, response);
    }

    public void destroy() {
    }
}