package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.AdminController;

public class ReturnDAO {

	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	
	/** 상품 주문시 리턴리스트에 데이터 추가 */
	public int insertData(ReturnVO rt) {
		int i = 0;
		sql.setLength(0);
		sql.append("insert into returnlist values(return_seq.nextval, ?,?,?,?,?,?,?,?)");
				//(seq, id, stylenum, rentaldate, returndate, name, phone, address, status)
		try {
			con = ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, rt.getId());
			pstmt.setString(2, rt.getStylenum());
			pstmt.setDate(3, new java.sql.Date(rt.getRentaldate().getTime()));
			pstmt.setDate(4, new java.sql.Date(rt.getReturndate().getTime()));
			pstmt.setString(5, rt.getName());
			pstmt.setString(6, rt.getPhone());
			pstmt.setString(7, rt.getAddress());
			pstmt.setString(8, rt.getStatus());
			i = pstmt.executeUpdate();
			System.out.println(i + "행 DB insert 성공");
			
		}catch(SQLException e) {
			System.err.println("쿼리문 에러 - DB insert 실패");
			e.printStackTrace();
		}finally { ConnUtil.closeAll(con, pstmt, rs);}
		return i;
	}
	
	
	/** 금일 수거 목록 조회 메서드 */
	public void getReturnTable() {
		RecentTableVO rt = null;
		sql.setLength(0);
		sql.append("select rentalnum, name, id, stylenum, status, address from returnlist " 
				+ "where trunc(returndate) = trunc(sysdate)");
		try {
			con = application.ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rt = new RecentTableVO();
				
				rt.setAddress(rs.getString("address"));
				rt.setStatus(rs.getString("status"));
				rt.setStylenum(rs.getString("stylenum"));
				rt.setRentalnum(rs.getInt("rentalnum")); 
				rt.setId(rs.getString("id"));
				rt.setName(rs.getString("name"));
				
				AdminController.recentList.add(rt);
			}
		}catch (SQLException e) {
			System.out.println("테이블 연동 실패");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
	}
	
	/** 미수거 목록 조회 메서드 */
	public void getOverdueTable() {
		RecentTableVO rt = null;
		sql.setLength(0);
		sql.append("select rentalnum, name, id, stylenum, status, address from returnlist " 
				+ "where trunc(returndate) < trunc(sysdate)");	//해결해야할 부분
		try {
			con = application.ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rt = new RecentTableVO();
				
				rt.setAddress(rs.getString("address"));
				rt.setStatus(rs.getString("status"));
				rt.setStylenum(rs.getString("stylenum"));
				rt.setRentalnum(rs.getInt("rentalnum")); 
				rt.setId(rs.getString("id"));
				rt.setName(rs.getString("name"));
				
				AdminController.recentList.add(rt);
			}
		}catch (SQLException e) {
			System.out.println("테이블 연동 실패");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
	}
	
	
}
