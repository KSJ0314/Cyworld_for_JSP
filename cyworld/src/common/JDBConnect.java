package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBConnect {
	public Connection con;	// DB 연결
	public Statement stmt;	// 정적 쿼리문 사용
	public PreparedStatement psmt;	// 동적 쿼리문 사용
	public ResultSet rs;	// 쿼리 실행 결과문 저장
	
	public JDBConnect() {
		try {
			// com.mysql.jdbc.Driver : mysql 드라이버 이름
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/cyworld?useUnicode=true&characterEncoding=utf8&useSSL=false";	// db 접속 주소
			String id = "manager";	// user Id
			String pwd = "1234";	// user Pw
			con = DriverManager.getConnection(url,id,pwd);	// db 연결	
			
			System.out.println("데이터 베이스 연결 성공");
			
		} catch (Exception e) {
			System.out.println("데이터 베이스 연결 오류입니다");
			e.printStackTrace();
		}
		
	}
	
	public void close() {
		try {
			if (rs != null) rs.close();			// 순서 중요
			if (stmt != null) stmt.close();
			if (psmt != null) psmt.close();
			if (con != null) con.close();
			System.out.println("자원 해제");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
