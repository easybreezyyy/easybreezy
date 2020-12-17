package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MemberDAO {
	
	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	Connection con = null;
	PreparedStatement pstmt = null; 
	
	public Map<String ,MemberVO> getMemberList() {		//db에 있는 전체 회원 정보 리스트
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
	
	
	
	public MemberVO getMember(String id) {		//id로 현재 멤버 검색
		MemberVO member = null;
		sql.setLength(0);
		sql.append("select * from members where id = ?");
		try {
			con = application.ConnUtil.getConnection();
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member = new MemberVO();
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setAddr(rs.getString("address"));
				member.setCard(rs.getString("card"));
				member.setPhone(rs.getString("phone"));
				member.setPassword(rs.getString("password"));
				System.out.println(member.toString());
			}
		} catch (SQLException e) {
			System.out.println("memberdao 회원찾기 실패");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		return member;
	}
	
	public int deleteMember(String id) {
//		MemberVO member = null;
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
	
	
}
