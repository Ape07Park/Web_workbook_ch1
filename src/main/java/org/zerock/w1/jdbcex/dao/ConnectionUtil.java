package org.zerock.w1.jdbcex.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public enum ConnectionUtil {
    INSTANCE;
    // DataSource == pool 따라서 HikariDataSource는 HikariPool 의미
    private HikariDataSource ds;

    // HikariCP 설정 객체 생성해 설정지정 및 지정한 설정 ds에 넣기
    ConnectionUtil() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/webdb");
        config.setUsername("webuser");
        config.setPassword("webuser");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config);
    }
    // 외부에서 사용 시 ConnectionUtil.INSTANCE.getConnection()으로 사용
    // db과 연결한 것 반환
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
