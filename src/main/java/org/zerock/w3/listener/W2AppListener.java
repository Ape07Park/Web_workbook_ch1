package org.zerock.w3.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class W2AppListener implements ServletContextListener {

    // 웹 어플리케이션이 시작 시 작동
    @Override
    public void contextInitialized(ServletContextEvent sce) { // ServletContextEvent 객체로 ServletContext에 접근할 수 있다
        log.info("--------------init-----------------");
        log.info("--------------init-----------------");
        log.info("--------------init-----------------");

        // 서블릿 컨텍스트: 웹 어플리케이션 내 모든 자원들을 같이 사용하는 공간 즉 애플리케이션 전체에서 공유할 설정과 데이터의 저장소이다.
        // 따라소 서블릿 컨텍스트에 저장한 것은 모든 서블릿과 JSP가 사용할 수 있다.
        // 서블릿 컨텍스트 객체 가져오기
        ServletContext servletContext = sce.getServletContext();

        // 서블릿 컨텍스트에 저장하기
        // 서블릿 JSP/EL에서 공유해서 사용 가능
        servletContext.setAttribute("appName", "W2");
    }

    // 웹 어플리케이션이 종료 시 작동
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("--------------destroy-----------------");
        log.info("--------------destroy-----------------");
        log.info("--------------destroy-----------------");
    }
}
