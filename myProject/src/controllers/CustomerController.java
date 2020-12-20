package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class CustomerController implements Initializable {
	
	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	Connection con = null;
	PreparedStatement pstmt = null; 
	
	MemberDAO memberdao = new MemberDAO();
	MemberVO member = null;
	Map<String, MemberVO> map;
	
	/**
	 * ���� ��� ������ �������ִ� �޼���
	 */
	public void setUser(String id) {
		this.member = memberdao.getMember(id);
		this.welcome.setText("WELCOME " + id);
		System.out.println(member.toString());
	}
	
	@FXML
	private AnchorPane pnRoot;
	
	@FXML
	private Text welcome;

	@FXML
	private JFXButton btItems;

	@FXML
	private JFXButton btCloset;

	@FXML
	private JFXButton btMyAccount;

	@FXML
	private JFXButton btLogout;

	@FXML
	private JFXButton btHome;

	@FXML
	private AnchorPane pnItems;

	@FXML
	private ImageView imageItem00;

	@FXML
	private Label lbBrand00;

	@FXML
	private Label lbItemname00;

	@FXML
	private ImageView imageItem10;

	@FXML
	private Label lbBrand10;

	@FXML
	private Label lbItemname10;

	@FXML
	private ImageView imageitem01;

	@FXML
	private Label lbBrand01;

	@FXML
	private Label lbItemname01;

	@FXML
	private ImageView imageItem11;

	@FXML
	private Label lbBrand11;

	@FXML
	private Label lbItemname11;

	@FXML
	private ImageView imageItem20;

	@FXML
	private Label lbBrand20;

	@FXML
	private Label lbItemname20;

	@FXML
	private ImageView imageItem21;

	@FXML
	private Label lbBrand21;

	@FXML
	private Label lbItemname21;

	@FXML
	private JFXTextField tfSearch;

	@FXML
	private JFXButton btsearch;

	@FXML
	private AnchorPane pnCloset;

	@FXML
	private Label lbCountRemain;

	@FXML
	private AnchorPane pnMyAccount;

	@FXML
	private JFXPasswordField tfAccountPassword;

	@FXML
	private JFXTextField tfAccountPhone;

	@FXML
	private JFXPasswordField tfAccountConfirmpwd;

	@FXML
	private JFXTextField tfAccountAddr;

	@FXML
	private JFXTextField tfAccountCard;

	@FXML
	private Label lbAccountId;

	@FXML
	private Label lbAccountName;

	@FXML
	private JFXButton btUpdate;

	@FXML
	private JFXButton btUnsubscribe;

	@FXML
	private Label lbCompleteUpdate;

	@FXML
	private AnchorPane pnHome;
	
	@FXML
	private StackPane pnStack;
	
    @FXML
    private AnchorPane pnSure;

    @FXML
    private JFXButton btCancel;

    @FXML
    private JFXButton btSureToUnsubscribe;

	public void handleCloset(ActionEvent event) {
		pnHome.setVisible(false);
		pnItems.setVisible(false);
		pnMyAccount.setVisible(false);
		pnCloset.setVisible(true);
		return;
	}

	@FXML
	void handleHome(ActionEvent event) {
		pnItems.setVisible(false);
		pnMyAccount.setVisible(false);
		pnCloset.setVisible(false);
		pnHome.setVisible(true);
		return;
	}

	@FXML
	void handleItems(ActionEvent event) {
		pnHome.setVisible(false);
		pnMyAccount.setVisible(false);
		pnCloset.setVisible(false);
		pnItems.setVisible(true);
	}

	@FXML
	void handleMyAccount(ActionEvent event) {
		pnHome.setVisible(false);
		pnItems.setVisible(false);
		pnCloset.setVisible(false);
		pnMyAccount.setVisible(true);
		lbAccountId.setText(member.getId());
		lbAccountName.setText(member.getName());
		tfAccountAddr.setText(member.getAddr());
		tfAccountCard.setText(member.getCard());
		tfAccountPhone.setText(member.getPhone());
		tfAccountPassword.setText("");
		tfAccountConfirmpwd.setText("");
		return;
	}
	
	

	public void handleLogout(ActionEvent event) {
		try {
			loadMain();
		} catch (IOException e) {
			System.out.println("���� �г� �ε� ����");
			e.printStackTrace();
		}
	}

	
	/**
	 * ��ǰ �˻�
	 */
	@FXML
	void handleSearch(ActionEvent event) {
		
	}

	/**
	 * ȸ�� Ż�� - ��ũ���� ��ư ������ ��
	 */
	@FXML
	void handleUnsubscribe(ActionEvent event) {
		pnSure.setVisible(true);
	}
	
	public void handleSureToUnsubscribe(ActionEvent event) {
		int i = memberdao.deleteMember(member.getId());
		System.out.println("���������� Ż��Ǿ����ϴ� : " + i);
		
		String alert = "���������� Ż��Ǿ����ϴ�.\nȮ���� �����ø� ����ȭ������ ���ư��ϴ�.";
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXButton button = new JFXButton("OK");
		JFXDialog dialog = new JFXDialog(pnStack, dialogLayout, JFXDialog.DialogTransition.TOP);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{
			dialog.close();
				try {
					loadMain();
				} catch (IOException e) {
					System.out.println("���� �г� �ε� ����");
					e.printStackTrace();
				}
			});
		
		dialogLayout.setBody(new Text(alert));
		dialogLayout.setActions(button);
		dialog.show();
	}

	public void handleCancel(ActionEvent event) {
		pnSure.setVisible(false);
	}
	
	/**
	 * ȸ������ ���� - ������Ʈ ��ư ������ ��
	 */
	@FXML
	void handleUpdate(ActionEvent event) {
		String newPassword = tfAccountPassword.getText().trim();
		String alert = "���������� �����Ǿ����ϴ�.";
		if(newPassword.length() != 0 && newPassword.equals(tfAccountConfirmpwd.getText().trim())) {
			String newAddr = tfAccountAddr.getText();
			String newPhone = tfAccountPhone.getText().trim();
			String newCard = tfAccountPhone.getText().trim();
			
			member.setAddr(newAddr);
			member.setCard(newCard);
			member.setPassword(newPassword);
			member.setPhone(newPhone);
			
			int i = memberdao.updateMember(member);
			System.out.println("customerController���� Ȯ�� : " + i);
			
		}else
			alert = "��й�ȣ�� ����� �Է��ߴ��� Ȯ�����ּ���.";
		
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXButton button = new JFXButton("OK");
		JFXDialog dialog = new JFXDialog(pnStack, dialogLayout, JFXDialog.DialogTransition.TOP);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{dialog.close();});
		
		dialogLayout.setBody(new Text(alert));
		dialogLayout.setActions(button);
		dialog.show();
	}

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setUser(MainController.id());
		btHome.setOnAction(event->handleHome(event));
		btItems.setOnAction(event->handleItems(event));
		btCloset.setOnAction(event->handleCloset(event));
		btMyAccount.setOnAction(event->handleMyAccount(event));
		btLogout.setOnAction(event->handleLogout(event));
		btsearch.setOnAction(event->handleSearch(event));
		btUpdate.setOnAction(event->handleUpdate(event));
		btUnsubscribe.setOnAction(event->handleUnsubscribe(event));
		btSureToUnsubscribe.setOnAction(event->handleSureToUnsubscribe(event));
		btCancel.setOnAction(event->handleCancel(event));
	}



	public void loadMain() throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		pnRoot.getChildren().setAll(pane);
	}
	
}
