package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.AdminController;
import controllers.CustomerController;

/** DB기반 RentalList CRUD 구현 클래스 */
public class RentalDAO {
	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	
	/** 상품 주문시 렌탈리스트에 데이터 추가 */
	public int insertData(RentalVO rental) {
		int i = 0;
		sql.setLength(0);
		sql.append("insert into rentallist values(rental_seq.nextval, ?,?,?,?,?,?,?,?)");
				//(seq, id, stylenum, rentaldate, returndate, name, phone, address, status)
		try {
			con = ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, rental.getId());
			pstmt.setString(2, rental.getStylenum());
			pstmt.setDate(3, new java.sql.Date(rental.getRentaldate().getTime()));
			pstmt.setDate(4, new java.sql.Date(rental.getReturndate().getTime()));
			pstmt.setString(5, rental.getName());
			pstmt.setString(6, rental.getPhone());
			pstmt.setString(7, rental.getAddress());
			pstmt.setString(8, rental.getStatus());
			i = pstmt.executeUpdate();
			System.out.println(i + "행 DB insert 성공");
			
		}catch(SQLException e) {
			System.err.println("쿼리문 에러 - DB insert 실패");
			e.printStackTrace();
		}finally { ConnUtil.closeAll(con, pstmt, rs);}
		return i;
	}
	
	/** 테이블 연동을 위한 메서드 
	 *  CustomerController - RentalList
	 *  Closet 메뉴에 보일 테이블
	 */
	public void getRentalTable(String id) {
		RentalTableVO rt = null;
		sql.setLength(0);
		sql.append("select r.rentalnum, to_char(r.rentaldate,'RRRR/MM/DD') as rentaldate, "
				+ "to_char(r.returndate, 'RRRR/MM/DD') as returndate, "
				+ "r.status, i.itemname, i.brand from rentallist r, items i "  
				+ "where r.stylenum = i.stylenum and r.id = ? order by rentalnum desc");
		try {
			con = application.ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rt = new RentalTableVO();

				rt.setBrand(rs.getString("brand"));
				rt.setStatus(rs.getString("status"));
				rt.setItemName(rs.getString("itemname"));
				rt.setRentalnum(rs.getInt("rentalnum"));
				rt.setReturnDate(rs.getString("returndate"));
				rt.setRentalDate(rs.getString("rentaldate"));
				
				CustomerController.rentalList.add(rt);
			}
		}catch (SQLException e) {
			System.out.println("테이블 연동 실패");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		
	}
	
	/** 테이블 연동을 위한 메서드
	 * AdminContoller - RecentList
	 */
	public void getRecentTable() {
		RecentTableVO rt = null;
		sql.setLength(0);
		sql.append("select rentalnum, name, id, stylenum, status, address from rentallist " 
				+ "where trunc(rentaldate) = trunc(sysdate)");
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
	
	/** 주문번호 조회 - ReturnVO에 값을 할당해주기 위한 메서드 */
	public int curRentalnum() {
		int i = 0;
		sql.setLength(0);
		sql.append("select rentalnum from rentallist where rownum <= 1 order by rentalnum desc");
		try {
			con = application.ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				i = rs.getInt(1);
			}
			System.out.println(i);
		}catch (SQLException e) {
			System.out.println("테이블 연동 실패");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		
		return i;
	}
	
}
