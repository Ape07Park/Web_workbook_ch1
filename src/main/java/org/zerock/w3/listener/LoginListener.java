package org.zerock.w3.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.log4j.Log4j2;

@WebListener
@Log4j2
public class LoginListener implements HttpSessionAttributeListener {

    /**
     * 세션에 속성이 추가되었을 때 감지 및 처리하는 메소드
     * @param event 세션과 추가된 속성의 이름 및 값을 지닌 객체
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

        // 세션에 바인딩(값이 할당된 것) 된 것을 하나의 이벤트로 보고 그것을 객체에 담음. 즉 세션에 속성이 추가, 삭제되는 걸 감지해서 그것을 객체에 담는 것

        // 그 이벤트 즉 세션에 올라간 값의 이름을 가져옴
        String name = event.getName();
        // 이벤트 즉 세션에 올라간 값을 가져옴
        Object value = event.getValue();

        if (name.equals("loginInfo")) {

            log.info("A user logined......");
            log.info(value);
        }
    }
}
