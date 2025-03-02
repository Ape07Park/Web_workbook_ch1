package org.zerock.w3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@WebServlet("/logout")
@Log4j2
public class LogoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info(".........../logout");

        // 세션 객체 생성
        HttpSession session = request.getSession();

        // loginInfo를 세션 저장소에서 제거
        session.removeAttribute("loginInfo");

        // 생성한 세션 객체를 무효화하고 삭제
        session.invalidate();

        // 성공 후 리스트로 이동
        response.sendRedirect("/");
    }

}
