package org.zerock.w3.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

// 필터를 사용하는 이유는 모든 컨트롤러에 하나하나 로그인 여부를 체크하는 로직을 둘 수 없어서 로그인 체크는 따로 필터로 분리함
@WebFilter(urlPatterns = {"/*"})
@Log4j2
// 한글 깨짐 방지를 위해 모든 요청이 UTF-8로 인코딩이 되도록 필터 설정
public class UTF8Filter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 서블릿 실행 전에 해야할 작업
        log.info("UTF8Filter");

        // HTTP 요청과 응답을 담을 객체 생성(다운캐스팅)
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // UTF-8로 인코딩하기
        request.setCharacterEncoding("UTF-8");

        // 다음 필터호출 or 요청이 목적지로 갈 수 있도록 함
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
