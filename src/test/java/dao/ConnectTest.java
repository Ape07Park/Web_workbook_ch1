package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectTest {
    
    // DB 연결 테스트
    @Test // 테스트 코드 or 테스트 메소드라 부름 반드시 public으로 선언하며 파라미터나 리턴타입 x
    public void test1() {
        int v1 = 10;
        int v2 = 10;

        // 같다고 확신하는지 확인, 두 변수의 값이 같아야 테스트 통과
        Assertions.assertEquals(v1, v2);
    }

    @Test
    public void testConnection() throws  Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 로딩. DB마다 클래스 이름 다름

        // 특정한 DB에 연결
        Connection conn = DriverManager.getConnection( // DB와의 네트워크 연결을 conn에 저장
                "jdbc:mysql://localhost:3306/webdb", // DB의 url을 의미. mysql jdbc 프로토콜을 이용/포트번호(네트워크 연결번호)/연결할 db 이름
                "webuser", // 사용자 id
                "webuser"); // 사용자 password

        // conn 객체가 null인지 아닌지 테스트
        Assertions.assertNotNull(conn);

        conn.close(); // DB와 연결 끊기

    }

    @Test
    public void testHikariCP() throws  Exception {
        
        // Connection Pool 설정 객체
        HikariConfig config = new HikariConfig();

        // Connection Pool 설정
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/webdb");
        config.setUsername("webuser");
        config.setPassword("webuser");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

//        HikariDataSource는 HikariPool 의미
        HikariDataSource ds = new HikariDataSource(config);
        Connection conn = ds.getConnection();

        System.out.println(conn);
        conn.close();
    }


}