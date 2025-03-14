package springex;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.springex.mapper.TimeMapper;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TimeMapperTests {

    @Autowired(required = false) // required = false면 객체 주입이 안되어도 예외가 발생 안한다. required = false를
    // 여기다 넣은 이유는 TimeMapper는 스프링 빈으로 등록된 것이 아니기 때문이다.
    private TimeMapper timeMapper;

    @Test
    public void testGetTime() {
        log.info("Current time: {}", timeMapper.getTime());
    }
}
