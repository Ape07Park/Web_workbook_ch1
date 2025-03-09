import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.sample.SampleService;

import javax.sql.DataSource;
import java.sql.Connection;

@Log4j2
@ExtendWith(SpringExtension.class) // 스프링 테스트를 이용하기 위한 설정 
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml") // 스프링 설정 정보 로딩 위해 사용한다.
// xml이라 locations를 사용하고 자바 클래스를 통해 설정 시 classes 사용 (@ContextConfiguration(classes = "ConfigClass")
public class SampleTests {

    // 필드 주입 방식
    @Autowired // 해당 타입의 빈이 존재하면 여기 주입할 것
    private SampleService sampleService;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testService1() {

        log.info(sampleService);
        Assertions.assertNotNull(sampleService);
    }

    @Test
    public void testConnetion() throws Exception {

        Connection connection = dataSource.getConnection();
        log.info(connection);
        Assertions.assertNotNull(connection);

        connection.close();
    }

}
