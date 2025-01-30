package org.zerock.w1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
// 어노테이션은 코드에 대한 추가 설정이나 처리 위해 사용
// value로 경로 지정
@WebServlet(name = "myServlet", value = "/my") // 사용 이유: 특정 브라우저 주소에 해당 서블릿 연결
public class MyServlet extends HttpServlet {
    
    @Override
    // doGet: 브라우저 주소 직접 변경해서 접근
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        response.setContentType("text/html");

        // PrintWriter로 브라우저에 문자(html)로 출력이 되도록 한다
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "MyServlet" + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}