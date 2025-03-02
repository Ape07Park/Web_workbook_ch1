package org.zerock.w3.dao;

import lombok.Cleanup;
import org.zerock.w3.domain.MemberVo;
import org.zerock.w3.domain.TodoVo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    public MemberVo findByMemberByMidAndMpw(String mid, String mpw) throws Exception {

        MemberVo vo;
        
        String sql = "select * from tbl_member where mid =? and mpw=?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

       
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, mid);
        preparedStatement.setString(2, mpw);

        //실행 결과를 resultSet에 담기
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        // 행 한번 이동
        resultSet.next();

         vo = MemberVo.builder()

                 .mid(resultSet.getString("mid"))
                 .mpw(resultSet.getString("mpw"))
                 .mname(resultSet.getString("mname"))
                 .build();

            return vo;
    }

    public void updateUuid(String mid, String uuid) throws Exception {

        String sql = "update tbl_member set uuid=? where mid = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, uuid);
        preparedStatement.setString(2, mid);

        preparedStatement.executeUpdate();
    }

    public MemberVo findUuidByUuid(String uuid) throws Exception {

        String sql = "select * from tbl_member where mid = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, uuid);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        MemberVo memberVo = MemberVo.builder()
                .mid(resultSet.getString("mid"))
                .mpw(resultSet.getString("mpw"))
                .mname(resultSet.getString("mname"))
                .uuid(resultSet.getString("uuid"))
                .build();

        return memberVo;
    }

}
