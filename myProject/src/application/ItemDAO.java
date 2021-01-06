package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB를 기반으로 하는 상품 CRUD 구현 클래스
 */
public class ItemDAO {

	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	Connection con = null;
	PreparedStatement pstmt = null; 
	
	/**
	 * 품번을 입력해 해당 상품 정보를 불러오는 클래스
	 * @param stylenum
	 * @return ItemVO
	 */
	public ItemVO getItem(String stylenum) {
		ItemVO item = null;
		sql.setLength(0);
		sql.append("select * from items where stylenum = ?");
		try {
			con = application.ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, stylenum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				item = new ItemVO();
				
				item.setStylenum(stylenum);
				item.setBrand(rs.getString("brand"));
				item.setImagepath(rs.getString("imagepath"));
				item.setItemname(rs.getString("itemname"));
				item.setPrice(rs.getInt("price"));
				item.setStock(rs.getInt("stock"));
				
				System.out.println(item.toString());
				
			}
		}catch(SQLException e) {
			System.err.println("db 쿼리문 틀려서 item 정보 로딩 실패");
			e.printStackTrace();
		}finally { application.ConnUtil.closeAll(con, pstmt, rs);}
		
		return item;
	}
	
	/**
	 * 상품 추가
	 * @param itemVO
	 * @return 추가된 행 수
	 */
	public int insertItem(ItemVO item) {
		int i = 0;
		sql.setLength(0);
		sql.append("insert into items values(?,?,?,?,?,?)");
		try {
			con = application.ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, item.getStylenum());
			pstmt.setString(2, item.getItemname());
			pstmt.setString(3, item.getBrand());
			pstmt.setInt(4, item.getStock());
			pstmt.setString(5, item.getImagepath());
			pstmt.setInt(6, item.getPrice());
			i = pstmt.executeUpdate();
			System.out.println("DAO에서 확인. 추가된 행 : "+ i);
		}catch(SQLException e) {
			System.err.println("db 쿼리문 틀려서 item 정보 로딩 실패");
			e.printStackTrace();
		}finally { application.ConnUtil.closeAll(con, pstmt, rs);}
		return i;	
	}
	
	
	/**
	 * 상품 삭제
	 * @param stylenum
	 * @return the number of deleted rows
	 */
	public int deleteItem(String stylenum) {
		int i = 0;
		sql.setLength(0);
		sql.append("delete from items where stylenum = ?");
		try {
			con = application.ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, stylenum);
			i = pstmt.executeUpdate();
			System.out.println("DAO에서 확인. 삭제된 행 : "+ i);
		}catch(SQLException e) {
			System.err.println("db 쿼리문 틀려서 item 삭제 실패");
			e.printStackTrace();
		}finally { application.ConnUtil.closeAll(con, pstmt, rs);}
		return i;	
	}
	
	/**
	 * 상품 정보 수정
	 * @param itemVO
	 * @return 수정된 행 수
	 */
	public int updateItem(ItemVO item) {
		int i = 0;
		sql.setLength(0);
		sql.append("update items set itemname = ?, brand = ?, stock = ?, imagepath = ?, price = ? where stylenum = ?");
		try {
			con = application.ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, item.getItemname());
			pstmt.setString(2, item.getBrand());
			pstmt.setInt(3, item.getStock());
			pstmt.setString(4, item.getImagepath());
			pstmt.setInt(5, item.getPrice());
			pstmt.setString(6, item.getStylenum());
			i = pstmt.executeUpdate();
			System.out.println("DAO에서 확인. 수정된 행 : "+ i);
		}catch(SQLException e) {
			System.err.println("db 쿼리문 틀려서 item 삭제 실패");
			e.printStackTrace();
		}finally { application.ConnUtil.closeAll(con, pstmt, rs);}
		return i;	
	}
	
	/**
	 * 상품 추가시 - 품번 중복여부 확인 메서드
	 */
	public int checkDuplicate(String stylenum) {
		int i = 0;
		sql.setLength(0);
		sql.append("select count(*) from items where stylenum = ?");
		try {
			con = application.ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, stylenum);
			rs = pstmt.executeQuery();
			
			while(rs.next())
				i = rs.getInt(1);
			System.out.println(i);
		}catch(SQLException e) {
			System.err.println("db 쿼리문 틀려서 item 정보 조회 실패");
			e.printStackTrace();
		}finally { application.ConnUtil.closeAll(con, pstmt, rs);}
		
		return i;
	}

	/**
	 * 상품 리스트 세팅 메서드
	 */
	public void setItemList() {
		sql.setLength(0);
		sql.append("select * from items order by stylenum");
		try {
			con = application.ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ItemVO item = new ItemVO(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getInt(4), rs.getString(5), rs.getInt(6));
				
				System.out.println(item.toString());
				
				if(rs.getInt(4) > 0)	//재고가 있을 경우에만 사용자 화면에 추가
					controllers.CustomerController.allItems.add(item);

				controllers.AdminController.itemList.add(item);
			}
		}catch(SQLException e) {
			System.err.println("db 쿼리문 틀려서 item 정보 조회 실패");
			e.printStackTrace();
		}finally { application.ConnUtil.closeAll(con, pstmt, rs);}
	}
	
	/**
	 * 재고가 많은 순으로 정렬한 상품 리스트 세팅 메서드
	 */
	public void orderByStock() {
		sql.setLength(0);
		sql.append("select * from items where stock > 0 order by stock desc");
		try {
			con = application.ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ItemVO item = new ItemVO(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getInt(4), rs.getString(5), rs.getInt(6));
				
				System.out.println(item.toString());
				
				controllers.CustomerController.allItems2.add(item);
			}
		}catch(SQLException e) {
			System.err.println("db 쿼리문 틀려서 item 정보 조회 실패");
			e.printStackTrace();
		}finally { application.ConnUtil.closeAll(con, pstmt, rs);}
	}

	/**
	 * 브랜드명으로 검색
	 */
	public void searchByBrand(String brand) {
		controllers.CustomerController.allItems3.clear();
		sql.setLength(0);
		sql.append("select * from items where stock > 0 and brand like '%' || ? || '%'");
		try {
			con = application.ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, brand);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ItemVO item = new ItemVO(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getInt(4), rs.getString(5), rs.getInt(6));
				
				System.out.println(item.toString());
				
				controllers.CustomerController.allItems3.add(item);
			}
		}catch(SQLException e) {
			System.err.println("db 쿼리문 틀려서 item 정보 조회 실패");
			e.printStackTrace();
		}finally { application.ConnUtil.closeAll(con, pstmt, rs);}
	}
	
	
	/** 상품 주문시 재고 반영 */
	public int rentItem(ItemVO item) {
		int i = 0;
		sql.setLength(0);
		sql.append("update items set stock = ? where stylenum = ?");
		try {
			con = ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, item.getStock());
			pstmt.setString(2, item.getStylenum());
			i = pstmt.executeUpdate();
			System.out.println("DAO에서 확인. 수정된 행 : "+ i);
		}catch(SQLException e) {
			System.err.println("db 쿼리문 틀려서 item 삭제 실패");
			e.printStackTrace();
		}finally { application.ConnUtil.closeAll(con, pstmt, rs);}
		return i;	
	}
	
	
}

