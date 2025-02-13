package org.zerock.w1.jdbcex.dao;

import lombok.Cleanup;
import org.zerock.w1.jdbcex.domain.TodoVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {

    public String getTime() {

        String now = null;

        // try 내부에 선언된 것들이 자동으로 close() 되게 try-with-resource 구조 사용
        // 구문이 종료될 때 AutoCloseable 인터페이스의 close()가 호출
        try (// db와의 연결
             Connection connection = ConnectionUtil.INSTANCE.getConnection();
             // sql문 실행
             PreparedStatement preparedStatement = connection.prepareStatement("select now()");
             // 실행 결과
             ResultSet resultSet = preparedStatement.executeQuery()) {
//            첫 번째 행으로 이동
            resultSet.next();

            // 결과의 인덱스가 1인 컬럼 - 첫 컬럼 가져오기
            now = resultSet.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

//    @Cleanup : 해당 메소드가 종료되면 close()가 자동으로 호출됨.
    public String getTime2() throws Exception { // throws Exception 추가

        String now = null;

        // db와의 연결
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        // 실행할 sql문 설정
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("select now()");

        // 실행 결과
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
//            첫 번째 행으로 이동
        resultSet.next();

        // 결과의 인덱스가 1인 컬럼 - 첫 컬럼 가져오기
        now = resultSet.getString(1);

        return now;
    }

    // 삽입 
    public void insert(TodoVo vo) throws Exception {

        String sql = "insert into tbl_todo (title, dueDate, finished) values (?, ?, ?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

        // 실행할 sql문 설정
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // ?에 들어갈 sql문의 파라미터 세팅
        preparedStatement.setString(1, vo.getTitle());
        preparedStatement.setDate(2, Date.valueOf(vo.getDueDate())); // sql에는 LocalDate라는 타입을 지원 x 따라서 sql 타입에 맞게 dueDate를 변환
        preparedStatement.setBoolean(3, vo.isFinished());

        // sql 실행
        preparedStatement.executeUpdate();
    }
    
    // 모든 데이터 가져오기
    public List<TodoVo> selectAll() throws Exception {
        
        String sql = "select * from tbl_todo";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

        // 실행할 sql문 설정
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        //실행 결과를 resultSet에 담기
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<TodoVo> todoList = new ArrayList<>();

         // resultSet에 있는 row(행)를 반복문을 통해 vo에 담기
        while (resultSet.next()) { // .next()는 이동할 행이 있으면 true 없으면 false
            TodoVo vo = TodoVo.builder()
                    .tno(resultSet.getLong("tno"))
                    .title(resultSet.getString("title"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished"))
                    .build();

            todoList.add(vo);
        }
            return todoList;
    }
    
    public TodoVo selectOne(Long tno) throws Exception {

        TodoVo vo;
        
        String sql = "select * from tbl_todo where tno =?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

       
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1,  tno);

        //실행 결과를 resultSet에 담기
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        // 행 한번 이동
        resultSet.next();

         vo = TodoVo.builder()
                 .tno(resultSet.getLong("tno"))
                 .title(resultSet.getString("title"))
                 .dueDate(resultSet.getDate("dueDate").toLocalDate())
                 .finished(resultSet.getBoolean("finished"))
                 .build();

            return vo;
    }

    // update
    public void update(TodoVo vo) throws Exception {

        String sql = "update tbl_todo set title =?, dueDate =?, finished =? where tno =?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

        // 실행할 sql문 설정
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // ?에 들어갈 sql문의 파라미터 세팅
        preparedStatement.setString(1, vo.getTitle());
        preparedStatement.setDate(2, Date.valueOf(vo.getDueDate()));
        preparedStatement.setBoolean(3, vo.isFinished());
        preparedStatement.setLong(4, vo.getTno());

        // sql 실행
        preparedStatement.executeUpdate();
    }

    //delete
    public void delete(Long tno) throws Exception {

        String sql = "delete from tbl_todo where tno =?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

        // 실행할 sql문 설정
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1,  tno);

        // sql 실행
        preparedStatement.executeUpdate();
    }
}
