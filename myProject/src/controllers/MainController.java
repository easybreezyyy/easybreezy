package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.MemberVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable {
	
	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	Connection con = null;
	PreparedStatement pstmt = null; 
	Boolean duplicatedId = false;
	
	private MemberVO member = new MemberVO();
	private Map<String, MemberVO> map = new HashMap<>();

	@FXML
	private AnchorPane pnRoot;
	@FXML
	private AnchorPane pnRegister;
	@FXML
	private AnchorPane pnSignin;

	@FXML
	private JFXTextField tfIdRegister;

	@FXML
	private JFXPasswordField tfPwdRegister;

	@FXML
	private JFXPasswordField tfConfrimPwd;

	@FXML
	private JFXTextField tfName;

	@FXML
	private JFXTextField tfPhone;

	@FXML
	private JFXTextField tfAddr;

	@FXML
	private JFXTextField tfCard;

	@FXML
	private JFXButton btBacktoSignin;

	@FXML
	private JFXButton btRegister;

	@FXML
	private JFXButton btIdCheck;

	@FXML
	private JFXTextField tfId;

	@FXML
	private JFXPasswordField tfPwd;

	@FXML
	private JFXButton btSignin;

	@FXML
	private JFXButton btGotoRegister;

	@FXML
	private Label lbCheckSignin;

	@FXML
	public void handleBacktoSignin(ActionEvent event) {
		pnRegister.setVisible(false);
		pnSignin.setVisible(true);
		tfId.requestFocus();
		tfAddr.setText("");
		tfCard.setText("");
		tfConfrimPwd.setText("");
		tfIdRegister.setText("");
		tfName.setText("");
		tfPhone.setText("");
		tfPwdRegister.setText("");
	}

	@FXML
	public void handleGotoRegister(ActionEvent event) {
		pnSignin.setVisible(false);
		pnRegister.setVisible(true);
		tfIdRegister.requestFocus();
	}

	@FXML
	public void handleIdCheck(ActionEvent event) {
		try {
			sql.setLength(0);
			sql.append("select id from customer where id = ?");	//이 아이디를 db에서 검색
			con = application.ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tfIdRegister.getText().trim());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
					System.out.println("사용중인 아이디입니다.");
					duplicatedId = true;
			}else {
				if(tfIdRegister.getText().trim().equals("admin")) {
					System.out.println("사용할 수 없는 아이디입니다.");
					duplicatedId = true;					
				} else
					System.out.println("사용할 수 있는 아이디입니다.");
			}

		}catch (SQLException e) {
			System.out.println("check Query");
			e.printStackTrace();
		}
		try {if(con!= null) con.close();} catch(SQLException e) {} 
		try {if(pstmt!= null) pstmt.close();} catch(SQLException e) {} 
		try {if(rs!= null) rs.close();}catch(SQLException e) {}
	}

	@FXML
	public void handleRegister(ActionEvent event) {
		String id = tfIdRegister.getText();
		if(id.trim().length()==0) return;
		String password = tfPwdRegister.getText();
		if(password.trim().length()==0) return;
		String name = tfName.getText();
		if(name.trim().length()==0) return;
		String phone = tfPhone.getText();
		if(phone.trim().length()==0) return;
		String addr = tfAddr.getText();
		if(addr.trim().length()==0) return;
		String card = tfCard.getText();
		if(card.trim().length()==0) return;
		
		if(duplicatedId==false) {
			MemberVO member = new MemberVO(id,password,name,phone,addr,card);
			map.put(member.getId(), member);
			try {
				sql.setLength(0);
				sql.append("insert into customer values(?,?,?,?,?,?)");
				con = application.ConnUtil.getConnection();
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, member.getId());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setString(4, member.getPhone());
				pstmt.setString(5, member.getAddr());
				pstmt.setString(6, member.getCard());
				int i = pstmt.executeUpdate();
				System.out.println(i + "행이 추가되었습니다.");
			}catch(SQLException e) {
				System.out.println("db(customer 테이블)에 안들어감");
				e.printStackTrace();
			}
		}else if(!password.equals(tfConfrimPwd.getText())){
			System.out.println("비밀번호를 확인해주세요.");
		}else 
			System.out.println("중복된 아이디입니다.");
		
		try {if(pstmt!= null) pstmt.close();} catch(SQLException e) {} 
		try {if(con!= null) con.close();} catch(SQLException e) {} 
	}

	@FXML
	public void handleSignin(ActionEvent event) {
		try {
			sql.setLength(0);
			sql.append("select id, password from admin");
			con = application.ConnUtil.getConnection();
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());

//			pstmt = con.prepareStatement(sql.toString());
//			pstmt.setString(1, "id");
//			pstmt.setString(2, "password");
//			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString(1);
				String password = rs.getString(2);
				System.out.println(id);
				System.out.println(password);

				if (tfId.getText().equals(id) && tfPwd.getText().equals(password))
					try {
						loadAdmin();
					} catch (IOException e) {
						System.out.println("지또에?");
						e.printStackTrace();
					}
				else
					lbCheckSignin.setVisible(true);
			}
		} catch (SQLException e) {
			System.out.println("쿼리문 틀렸다");
			e.printStackTrace();
		}
		try {if(con!= null) con.close();} catch(SQLException e) {} 
		try {if(pstmt!= null) pstmt.close();} catch(SQLException e) {} 
		try {if(rs!= null) rs.close();}catch(SQLException e) {}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfId.requestFocus();
		btGotoRegister.setOnAction(event -> handleGotoRegister(event));
		btSignin.setOnAction(event -> handleSignin(event));
		btRegister.setOnAction(event -> handleRegister(event));
		btBacktoSignin.setOnAction(event -> handleBacktoSignin(event));
	}

	public void loadAdmin() throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/AdminMain.fxml"));
		pnRoot.getChildren().setAll(pane);
	}

}
