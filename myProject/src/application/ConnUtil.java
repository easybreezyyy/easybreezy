package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnUtil {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			System.out.println("DB연동실패");
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "myproject", "myproject");
	}
	
	public static void closeAll(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {if(con!= null) con.close();} catch(SQLException e) {} 
		try {if(pstmt!= null) pstmt.close();} catch(SQLException e) {} 
		try {if(rs!= null) rs.close();}catch(SQLException e) {}
	}
	
}
