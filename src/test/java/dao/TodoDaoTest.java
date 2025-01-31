package dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.w1.jdbcex.domain.dao.TodoDao;

public class TodoDaoTest {

    private TodoDao todoDao;

    @BeforeEach // @Test 전에 @BeforeEach가 붙은 작업을 먼저 수행
    public void ready() {
        todoDao = new TodoDao();
    }

    @Test
    public void testTime() throws Exception {
        System.out.println(todoDao.getTime());
    }
}
