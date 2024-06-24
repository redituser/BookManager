package bookManager_re;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBConnection {
	
	public Connection conn;
	public PreparedStatement pstmt;
	public ResultSet rs;
	
	public JDBConnection() {
		
			final String jdbcDriverClassName = "oracle.jdbc.OracleDriver";
	        final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	        final String userid = "C##SYSO";
	        final String passwd = "SYSO";

	        // DB 연결 객체 생성하여 반환
	     

	        try {
	            // JDBC 드라이버 로딩
	            Class.forName(jdbcDriverClassName);

	            // Connection 객체 생성
	            this.conn = DriverManager.getConnection(url, userid, passwd);
	            System.out.println("오라클 연결 성공");
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }       
		
	}
	
	
	
	public void close() {
		try {
			if(conn != null)conn.close();
			if(pstmt != null) pstmt.close();
			if(rs != null)rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	

}
