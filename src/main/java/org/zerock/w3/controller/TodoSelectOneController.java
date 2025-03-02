package org.zerock.w3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.zerock.w3.dto.TodoDto;
import org.zerock.w3.service.TodoService;

import java.io.IOException;

@WebServlet(name = "todoSelectOneController", value = "/todo/view")
@Log4j2
public class TodoSelectOneController extends HttpServlet {

    private final TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            Long tno = Long.parseLong(request.getParameter("tno"));

            TodoDto todo = todoService.get(tno);

            request.setAttribute("todo", todo);

            // viewTodos 이름을 가진 쿠키가 있는지 확인 후 있으면 그대로 반환 없으면 쿠키 생성
            Cookie viewTodoCookie = findCookie(request.getCookies(), "viewTodos");

            // 쿠키 값 반환
            String todoListStr = viewTodoCookie.getValue();
            boolean exist = false;

            // 쿠키에 값이 존재하는지 확인
            if (todoListStr != null && todoListStr.contains(tno + "-")) {
                exist = true;
            }
            
            log.info("exists: " + exist);

            // 쿠키에 값이 없는 경우
            if (!exist) {
                todoListStr += tno + "-";
                // 값 넣기 ex) 1-2-
                viewTodoCookie.setValue(todoListStr);
                // 쿠기 지속시간 설정(24시간)
                viewTodoCookie.setMaxAge(60 * 60 * 24);
                // 어느 경로에서 쿠키를 사용할 것인지 설정
                viewTodoCookie.setPath("/");
                // 응답 객체에 생성한 쿠키 넣기
                response.addCookie(viewTodoCookie);
            }

            request.getRequestDispatcher("/WEB-INF/todo/view.jsp").forward(request, response);

        } catch (
                Exception e) {
            log.error("Todo select error", e);
            throw new ServletException(e);
        }

    }

    /**
     * 브라우저의 요청에 있는 viewTodos라는 쿠키가 있으면 그대로 두고 없으면 viewTodos라는 쿠키 생성 
     * @param cookies 브라우저의 요청에 딸려온 쿠키
     * @param name viewTodos
     * @return 쿠키
     */
    private Cookie findCookie(Cookie[] cookies, String name) {

        Cookie targetCookie = null;

        if (cookies != null && cookies.length > 0)  {

            for (Cookie ck : cookies) {
                if (ck.getName().equals(name)) {
                    targetCookie = ck;
                    break;
                }
            }
        }

        if (targetCookie == null) {
            targetCookie = new Cookie(name, "");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60 * 60 * 24);
        }
        
        return targetCookie;
    }
}
