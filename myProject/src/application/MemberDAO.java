package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import controllers.AdminController;

/**
 * DB�� ������� �ϴ� ȸ�� CRUD ���� Ŭ����
 * @author leejisoo
 *
 */
public class MemberDAO {
	
	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	Connection con = null;
	PreparedStatement pstmt = null; 
	
	/**
	 * ��ü ȸ�� ���� ����Ʈ
	 * @return map
	 */
	public Map<String ,MemberVO> getMemberList() {		
		Map<String, MemberVO> map = new HashMap<>();
		MemberVO member = null;
		sql.setLength(0);
		sql.append("select * from members");
		try {
			con = application.ConnUtil.getConnection();
			pstmt=con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member = new MemberVO();
				String id = rs.getString("id");
				member.setId(id);
				member.setName(rs.getString("name"));
				member.setAddr(rs.getString("address"));
				member.setCard(rs.getString("card"));
				member.setPhone(rs.getString("phone"));
				member.setPassword(rs.getString("password"));
				
				map.put(id, member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		return map;
	}
	
	
	/**
	 * id�� ���� ��� �˻��Ͽ� ��� ���� ��������
	 * @param id
	 * @return member
	 */
	public MemberVO getMember(String id) {		
		MemberVO member = null;
		sql.setLength(0);
		sql.append("select password, name, phone, address, card from members where id = ?");
		try {
			con = application.ConnUtil.getConnection();
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member = new MemberVO();
				String password = rs.getString("password");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String card = rs.getString("card");
				
				member.setId(id);
				member.setPassword(password);
				member.setAddr(address);
				member.setCard(card);
				member.setPhone(phone);
				member.setName(name);
				
				System.out.println(member.toString());
			}
		} catch (SQLException e) {
			System.out.println("memberdao ȸ��ã�� ����");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		return member;
	}
	
	/**
	 * ȸ�� ���� (Ż��)
	 * @param id
	 */
	public int deleteMember(String id) {
		int i = 0;
		sql.setLength(0);
		sql.append("delete from members where id = ?");
		try {
			con = application.ConnUtil.getConnection();
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			i = pstmt.executeUpdate();
			System.out.println(i + "���� ����Ǿ����ϴ�.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		
		return i;
		
	}
	
	/**
	 * ���� ��Ʈ�ѷ����� ȸ�����Խ� ȸ�� �߰�
	 * @param member
	 */
	public int insertMember(MemberVO member) {
		int i = 0;
		sql.setLength(0);
		sql.append("insert into members(id,password,name,phone,address,card) values(?,?,?,?,?,?)");
		try {
			con = application.ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getAddr());
			pstmt.setString(6, member.getCard());
			i = pstmt.executeUpdate();
			System.out.println(i + "���� �߰��Ǿ����ϴ�.");
		}catch(SQLException e) {
			System.out.println("db(customer ���̺�)�� �ȵ�");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		return i;
	}
	
	
	/**
	 * ȸ������ ����
	 * @param member
	 */
	public int updateMember(MemberVO member) {
		int i = 0;
		sql.setLength(0);
		sql.append("update members set password = ?, phone=?, address=?, card=? where id = ?");
		try {
			con = application.ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getAddr());
			pstmt.setString(4, member.getCard());
			pstmt.setString(5, member.getId());
			i = pstmt.executeUpdate();
			System.out.println(i + "���� �����Ǿ����ϴ�.");
			
		}catch(SQLException e) {
			System.out.println("db(customer ���̺�)�� �ȵ�");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		return i;
	}
	
	/**
	 * ���� ������ ȸ���� ī��Ʈ �޼���
	 */
	public int todayMember() {
		int i = 0;
		Statement stmt = null;
		sql.setLength(0);
		sql.append("select count(*) from members where trunc(registerdate) = trunc(sysdate)");
		try {
			con = application.ConnUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			
			while(rs.next()) {
				i = rs.getInt(1);
				System.out.println(i);
			}
		} catch (SQLException e) {
			System.out.println("ī��Ʈ ����");
			e.printStackTrace();
		}finally {
			try {if(con!= null) con.close();} catch(SQLException e) {} 
			try {if(stmt!= null) stmt.close();} catch(SQLException e) {} 
			try {if(rs!= null) rs.close();}catch(SQLException e) {}
			}
		return i;
	}
	
	/**
	 * ȸ������ - ���̺� ������ ���� �޼���
	 */
	public void getCustomerTable() {
		CustomerTableVO ct = null;
		sql.setLength(0);
		sql.append("select id,name,phone,card,status from members");
		try {
			con = application.ConnUtil.getConnection();
			pstmt=con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ct = new CustomerTableVO(); 

				String id = rs.getString("id");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String card = rs.getString("card");
				String status = rs.getString("status");
				
				ct.setId(id);
				ct.setName(name);
				ct.setPhone(phone);
				ct.setCard(card);
				ct.setStatus(status);
				
				AdminController.customerList.add(ct);
			}
		}catch (SQLException e) {
			System.out.println("���̺� ���� ����");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		
	}
	
}
