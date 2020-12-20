package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.MemberDAO;
import application.MemberVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


public class MainController implements Initializable {
	
	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	Connection con = null;
	PreparedStatement pstmt = null; 
	Boolean duplicatedId = false;
	
	MemberDAO memberdao = new MemberDAO();
	static MemberVO member = null;
	public static Map<String, MemberVO> map = new HashMap<>();
	
	public static String id() {
		return member.getId();
	}
	

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
	@FXML private Label lbCheckSignin1;

    @FXML
    private JFXButton btDeliverMode;
	
    @FXML
    private AnchorPane pnDeliverMode;

    @FXML
    private JFXTextField tfdId;

    @FXML
    private JFXPasswordField tfdPwd;

    @FXML
    private JFXButton btDeliverSignin;

    @FXML
    private JFXButton btCancel;
    
    @FXML
    private StackPane pnStack;

    
    public void handleCancel(ActionEvent event) {
    	pnDeliverMode.setVisible(false);
    	pnRegister.setVisible(false);
    	pnSignin.setVisible(true);
    	btCancel.setVisible(false);
    	btDeliverMode.setVisible(true);
    	tfId.requestFocus();
    	return;
    }
    
    public void handleDeliverSignin(ActionEvent event){
    	try {
			sql.setLength(0);
			sql.append("select password from delivers where id = ?");
			con = application.ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tfdId.getText().trim());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {	//���̵� ���� 
				String password = rs.getString(1);
				System.out.println(password);
	
				if(tfdPwd.getText().trim().equals(password))	{//��й�ȣ ������
					try {
						loadDeliver();
					} catch (IOException e) {
						System.out.println("Deliver Panel �ε� ����");
						e.printStackTrace();
					}
				}		
			}//end while ���̵� ����
			lbCheckSignin.setVisible(true);
		} catch (SQLException e) {
			System.out.println("������ Ʋ�ȴ�");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
    	return;
    }
    
	 
	@FXML
	public void handleBacktoSignin(ActionEvent event) {
		pnRegister.setVisible(false);
		pnDeliverMode.setVisible(false);
		pnSignin.setVisible(true);
		tfId.requestFocus();
		tfAddr.setText("");
		tfCard.setText("");
		tfConfrimPwd.setText("");
		tfIdRegister.setText("");
		tfName.setText("");
		tfPhone.setText("");
		tfPwdRegister.setText("");
		return;
	}

	@FXML
	public void handleGotoRegister(ActionEvent event) {
		pnSignin.setVisible(false);
		pnRegister.setVisible(true);
		tfIdRegister.requestFocus();
		return;
	}

	@FXML
	public void handleIdCheck(ActionEvent event) {
		String alert = "����� �� �ִ� ���̵��Դϴ�.";
		try {
			sql.setLength(0);
			sql.append("select id from members where id = ?");	//�� ���̵� db���� �˻�
			con = application.ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tfIdRegister.getText().trim());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
					alert = "�̹� ������� ���̵��Դϴ�.";
					duplicatedId = true;
			}
			
			JFXDialogLayout dialogLayout = new JFXDialogLayout();
			JFXButton button = new JFXButton("OK");
			JFXDialog dialog = new JFXDialog(pnStack, dialogLayout, JFXDialog.DialogTransition.TOP);
			button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{dialog.close();});
			
			dialogLayout.setBody(new Text(alert));
			dialogLayout.setActions(button);
			dialog.show();


		}catch (SQLException e) {
			System.out.println("check Query");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		return;
	}

	@FXML
	public void handleRegister(ActionEvent event) {
		String alert = "ȸ�� ������ ���� ������ ���� ���� �Է��ߴ��� Ȯ�����ּ���.";
		Boolean flag = true;
		
		String id = tfIdRegister.getText();
		if(id.trim().length()==0) flag = false;
		String password = tfPwdRegister.getText();
		if(password.trim().length()==0)flag = false; 
		String name = tfName.getText();
		if(name.trim().length()==0)flag = false;
		String phone = tfPhone.getText();
		if(phone.trim().length()==0)flag = false;
		String addr = tfAddr.getText();
		if(addr.trim().length()==0)flag = false;
		String card = tfCard.getText();
		if(card.trim().length()==0)flag = false;
		
		if(flag==true) {
			if(duplicatedId==false) {
				MemberVO member = new MemberVO(id,password,name,phone,addr,card);
				map.put(member.getId(), member);
				int i = memberdao.insertMember(member);
				System.out.println("���ο��� Ȯ�� " + i);
				alert = "���� �Ϸ�.\n�α��� ���ּ���.";
					
				tfAddr.setEditable(false);
				tfCard.setEditable(false);
				tfConfrimPwd.setEditable(false);
				tfIdRegister.setEditable(false);
				tfName.setEditable(false);
				tfPhone.setEditable(false);
				tfPwdRegister.setEditable(false);
				
			}else if(!password.equals(tfConfrimPwd.getText())){
				alert = "��й�ȣ�� Ȯ�����ּ���.";
			}else {
				alert = "�ߺ��� ���̵��Դϴ�.";
			}
		}//end if flag
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXButton button = new JFXButton("OK");
		JFXDialog dialog = new JFXDialog(pnStack, dialogLayout, JFXDialog.DialogTransition.TOP);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{dialog.close();});
		
		dialogLayout.setBody(new Text(alert));
		dialogLayout.setActions(button);
		dialog.show();

		return;
	}

	@FXML
	public void handleSignin(ActionEvent event) {
		try {
			member = new MemberVO();
			sql.setLength(0);
			sql.append("select password from members where id = ?");
			con = application.ConnUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tfId.getText().trim());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {	//���̵� ���� 
				String id = tfId.getText().trim();
				System.out.println(id);
				String password = rs.getString(1);
				System.out.println(password);
	
				if(tfPwd.getText().trim().equals(password))	{//��й�ȣ ������
					if (tfId.getText().trim().equals("admin")) {
						try {
							loadAdmin();
						} catch (IOException e) {
							System.out.println("���ǿ�?");
							e.printStackTrace();
						}
					}else {		//customer
						try {
							member = memberdao.getMember(id);
							System.out.println(member.toString());
							map.put(id, member);
							loadCustomer();
						} catch (IOException e) {
							System.out.println("���ǿ�?");
							e.printStackTrace();
						}
					}
					
				}		
			}//end while ���̵� ����
			lbCheckSignin1.setVisible(true);
		} catch (SQLException e) {
			System.out.println("������ Ʋ�ȴ�");
			e.printStackTrace();
		}finally {application.ConnUtil.closeAll(con, pstmt, rs);}
		return;
	}

	public void handleDeliverMode(ActionEvent event) {
		pnSignin.setVisible(false);
		pnRegister.setVisible(false);
		pnDeliverMode.setVisible(true);
		btCancel.setVisible(true);
		btDeliverMode.setVisible(false);
		tfdId.requestFocus();
		return;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfId.requestFocus();
		btGotoRegister.setOnAction(event -> handleGotoRegister(event));
		btSignin.setOnAction(event -> handleSignin(event));
		btRegister.setOnAction(event -> handleRegister(event));
		btBacktoSignin.setOnAction(event -> handleBacktoSignin(event));
		btDeliverMode.setOnAction(event->handleDeliverMode(event));
		btCancel.setOnAction(event->handleCancel(event));
		btDeliverSignin.setOnAction(event->handleDeliverSignin(event));
	}


	public void loadAdmin() throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Admin.fxml"));
		pnRoot.getChildren().setAll(pane);
	}
	
	public void loadCustomer() throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Customer.fxml"));
		pnRoot.getChildren().setAll(pane);
	}

	public void loadDeliver() throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Deliver.fxml"));
		pnRoot.getChildren().setAll(pane);
	}


	
	
}
