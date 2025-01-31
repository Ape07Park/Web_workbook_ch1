package org.zerock.w1.jdbcex.domain.dao;

import lombok.Cleanup;
import org.zerock.w1.jdbcex.domain.TodoVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoDao {

    public String getTime() {

        String now = null;

        // try 내부에 선언된 것들이 자동으로 close() 되게 try-with-resource 구조 사용
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

    public String getTime2() throws Exception {

        String now = null;

        // db와의 연결
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        // sql문 실행
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("select now()");

        // 실행 결과
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
//            첫 번째 행으로 이동
        resultSet.next();

        // 결과의 인덱스가 1인 컬럼 - 첫 컬럼 가져오기
        now = resultSet.getString(1);

        return now;
    }
    
    // TODO 작업 중
    public void insert(TodoVo vo) throws Exception {
        String sql = "insert into tbl_";
    }
}
