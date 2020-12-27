package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.AdminController;
import controllers.CustomerController;

/** DB��� RentalList CRUD ���� Ŭ���� */
public class RentalDAO {
	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	
	/** ��ǰ �ֹ��� ��Ż����Ʈ�� ������ �߰� */
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
			System.out.println(i + "�� DB insert ����");
			
		}catch(SQLException e) {
			System.err.println("������ ���� - DB insert ����");
			e.printStackTrace();
		}finally { ConnUtil.closeAll(con, pstmt, rs);}
		return i;
	}
	
	/** ���̺� ������ ���� �޼��� 
	 *  CustomerController - RentalList
	 *  Closet �޴��� ���� ���̺�
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
			System.out.println("���̺� ���� ����");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		
	}
	
	/** ���̺� ������ ���� �޼���
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
			System.out.println("���̺� ���� ����");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		
	}
	
	/** �ֹ���ȣ ��ȸ - ReturnVO�� ���� �Ҵ����ֱ� ���� �޼��� */
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
			System.out.println("���̺� ���� ����");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		
		return i;
	}
	
}
