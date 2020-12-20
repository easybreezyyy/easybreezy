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
 * DB를 기반으로 하는 회원 CRUD 구현 클래스
 * @author leejisoo
 *
 */
public class MemberDAO {
	
	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	Connection con = null;
	PreparedStatement pstmt = null; 
	
	/**
	 * 전체 회원 정보 리스트
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
	 * id로 현재 멤버 검색하여 멤버 정보 가져오기
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
			System.out.println("memberdao 회원찾기 실패");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		return member;
	}
	
	/**
	 * 회원 삭제 (탈퇴)
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
			System.out.println(i + "행이 실행되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		
		return i;
		
	}
	
	/**
	 * 메인 컨트롤러에서 회원가입시 회원 추가
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
			System.out.println(i + "행이 추가되었습니다.");
		}catch(SQLException e) {
			System.out.println("db(customer 테이블)에 안들어감");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		return i;
	}
	
	
	/**
	 * 회원정보 수정
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
			System.out.println(i + "행이 수정되었습니다.");
			
		}catch(SQLException e) {
			System.out.println("db(customer 테이블)에 안들어감");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		return i;
	}
	
	/**
	 * 금일 가입한 회원수 카운트 메서드
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
			System.out.println("카운트 실패");
			e.printStackTrace();
		}finally {
			try {if(con!= null) con.close();} catch(SQLException e) {} 
			try {if(stmt!= null) stmt.close();} catch(SQLException e) {} 
			try {if(rs!= null) rs.close();}catch(SQLException e) {}
			}
		return i;
	}
	
	/**
	 * 회원정보 - 테이블 연동을 위한 메서드
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
			System.out.println("테이블 연동 실패");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		
	}
	
}
