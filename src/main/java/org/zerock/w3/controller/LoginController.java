package org.zerock.w3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.zerock.w3.dto.MemberDto;
import org.zerock.w3.dto.TodoDto;
import org.zerock.w3.service.MemberService;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/login")
@Log4j2
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info(".........../login GET");

        // 정상적인 로그인 시 이동
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info(".........../login POST");

        // 요청 객체에서 각 파라미터의 값 가져오기
        String mid = request.getParameter("mid");
        String pwd = request.getParameter("pwd");

        try {
            // id와 pwd로 회원 dto 생성
            MemberDto memberDto = MemberService.INSTANCE.login(mid, pwd);

            HttpSession session = request.getSession();
            // 세션에 회원 정보 저장
            session.setAttribute("loginInfo", memberDto);

            response.sendRedirect("/todo/list");
        } catch (Exception e) {
            response.sendRedirect("/login?result=error");
        }
    }

}
