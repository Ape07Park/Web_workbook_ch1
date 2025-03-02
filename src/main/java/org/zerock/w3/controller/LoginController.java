package org.zerock.w3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.log4j.Log4j2;
import org.zerock.w3.dto.MemberDto;
import org.zerock.w3.dto.TodoDto;
import org.zerock.w3.service.MemberService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

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
        
        // 요청 객체에서 remember-me 파라미터 가져오기 
        String rememberMe = request.getParameter("remember-me");
        
        // 체크박스 on이면 isRememberMe true로 바꾸기
        boolean isRememberMe = rememberMe != null && rememberMe.equals("on");

        try {
            // id와 pwd로 회원 dto 생성
            MemberDto memberDto = MemberService.INSTANCE.login(mid, pwd);
            
            if (isRememberMe == true) {
                
                // UUID(Universally unique identifier) : 범용 고유 식별자
                // uuid 생성
                String uuid = UUID.randomUUID().toString();
                
                // db에 uuid 삽입
                MemberService.INSTANCE.updateUuid(mid, uuid);

                memberDto.setUuid(uuid);

                Cookie rememberMeCookie = new Cookie("remember-me", uuid);
                rememberMeCookie.setPath("/");
                rememberMeCookie.setMaxAge(60 * 60 * 24);

                response.addCookie(rememberMeCookie);
            }

            HttpSession session = request.getSession();
            // 세션에 회원 정보 저장
            session.setAttribute("loginInfo", memberDto);

            response.sendRedirect("/todo/list");
        } catch (Exception e) {
            response.sendRedirect("/login?result=error");
        }
    }

}
