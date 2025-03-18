package dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoDaoTest {

//    private TodoDao todoDao;
//
//    @BeforeEach // @Test 전에 @BeforeEach가 붙은 작업을 먼저 수행
//    public void ready() {
//        todoDao = new TodoDao();
//    }
//
//    @Test
//    public void testTime() throws Exception {
//        System.out.println(todoDao.getTime());
//    }
//
//    @Test
//    public void testInsert() throws Exception {
//
//        TodoVo todoVo = TodoVo.builder().title("0213 sample Title")
//                .dueDate(LocalDate.of(2025, 2, 12))
//                .finished(false)
//                .build();
//
//
//        todoDao.insert(todoVo);
//    }
//
//    @Test
//    public void testList() throws Exception {
//
//        List<TodoVo> todoList = new ArrayList<TodoVo>();
//
//        todoList = todoDao.selectAll();
//
//
//        todoList.forEach(todo -> System.out.println(todo));
//
//        // 아래는 메소드 참조
////        todoList.forEach(System.out::println);
//
//    }
//
//    @Test
//    public void testOne() throws Exception {
//
//        TodoVo todo;
//
//        todo = todoDao.selectOne(15L);
//
//        System.out.println(todo);
//    }
//
//    @Test
//    public void testUdateOne() throws Exception {
//
//       TodoVo todo = TodoVo.builder()
//               .tno(15L)
//               .title("updated Title")
//               .dueDate(LocalDate.of(2025, 2, 13))
//               .finished(true)
//               .build();
//
//       todoDao.update(todo);
//
//    }
}
