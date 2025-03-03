package org.zerock.w3.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.zerock.w3.dto.MemberDto;
import org.zerock.w3.service.MemberService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

// 필터를 사용하는 이유는 모든 컨트롤러에 하나하나 로그인 여부를 체크하는 로직을 둘 수 없어서 로그인 체크는 따로 필터로 분리함
@WebFilter(urlPatterns = {"/todo/*"}) // todo/ 로 시작하는 모든 것에 필터를 적용
@Log4j2
public class LoginCheckFilter implements Filter {

    // 1) @WebFilter로 설정한 url에 요청이 들어오면 doFilter 메소드가 호출된다.(즉 인터셉터처럼 중산에 요청을 가로채 doFilter가 실행되도록 한다)
    // 2) 호출된 doFilter()에서 내가 구현한 기능이 작동한다
    // 3) filterChain은 다음 필터를 가리키고 filterChain.doFilter()는 다음 필터를 호출한다.
    // 이떄 만약 다음 필터에도 filterChain.doFilter()가 있다면 또 다음 필터를 호출한다. 필터가 없다면 요청한 리소스가 호출된다.
    // 서블릿이 실행되기 전에 처리할 작업은 filterChain.doFilter() 이전에, 서블릿이 실행된 후에 처리할 작업은 filterChain.doFilter() 이후에 작성한다.
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 서블릿 실행 전에 해야할 작업
        log.info("LoginCheckFilter");

        // HTTP 요청과 응답을 담을 객체 생성(다운캐스팅)
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 세션 객체 생성
        HttpSession session = request.getSession();

        if (session.getAttribute("loginInfo") != null) {

            // 다음 필터호출 or 요청이 목적지로 갈 수 있도록 함
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        /**
         * session에 loginInfo가 없을 경우
         */

        // 쿠키가 있으면 쿠키 반환 없으면 null 반환
        Cookie cookie = findCook(request.getCookies(), "remember-me");

        // 쿠키가 없을 경우
        if (cookie == null) {
            response.sendRedirect("/login");
            return;
        }

        // 쿠키가 존재하는 경우
        log.info("cookie가 존재한다");

        // uuid 값
        String uuid = cookie.getValue();

        try {
            // DB 확인해서 회원 정보 가져오기
            MemberDto memberDto = MemberService.INSTANCE.getByUuid(uuid);

            log.info("쿠키 값으로 조회한 사용자 정보: " + memberDto);

            if (memberDto == null) {
                throw new Exception("cookie value is not valid");
            }

            // 회원 정보를 세션에 추가
            session.setAttribute("loginInfo", memberDto);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/login");
        }


    }

    /**
     * 요청 객체에서 가져온 쿠키가 있으면 쿠키 반환 없으면 null 반환
     * @param cookies 요청 객체에서 가져온 쿠키
     * @param name remember-me
     * @return
     */
    private Cookie findCook(Cookie[] cookies, String name) {

        if (cookies == null && cookies.length == 0) {
            return null;
        }

        // Optional: null을 직접 다루는 것을 피하고, 안전하게 값의 존재 여부를 처리할 수 있도록 도와주는 래퍼 클래스 즉 null일 경우 NPE 방지 위해 사용
         // 스트림에서 remember-me 쿠키가 있으면 이름과 일치하는 첫번째 쿠키 꺼내기
        Optional<Cookie> result = Arrays.stream(cookies).filter(c -> c.getName().equals(name)).findFirst();

//        return result.isPresent() ? result.get() : null;
        // result에 값이 있으면 그 값을 꺼내고 없으면 null 꺼냄
        return result.orElse(null);
    }

}
