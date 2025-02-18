package service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.w1.jdbcex.dto.TodoDto;
import org.zerock.w1.jdbcex.service.TodoService;


import java.time.LocalDate;

@Log4j2
public class TodoServiceTests {

    private TodoService todoService;

    @BeforeEach
    public void ready() {
        todoService = TodoService.INSTANCE;
    }

    @Test
    public void testRegister() throws Exception {

        TodoDto todoDto = TodoDto.builder()
                .title("JDBC Test Title")
                .dueDate(LocalDate.now())
                .finished(false)
                .build();

        log.info("------------------------------");
        log.info(todoDto);

        todoService.register(todoDto);
    }
}
