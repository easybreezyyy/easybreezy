package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable {
	
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

	}

	@FXML
	public void handleRegister(ActionEvent event) {

	}

	@FXML
	public void handleSignin(ActionEvent event) {
		try {
			loadAdminMain();
		} catch (IOException e) {
			System.out.println("Áö¶Ç¿¡?");
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfId.requestFocus();
		btGotoRegister.setOnAction(event -> handleGotoRegister(event));
		btSignin.setOnAction(event -> handleSignin(event));
		btRegister.setOnAction(event -> handleRegister(event));
		btBacktoSignin.setOnAction(event -> handleBacktoSignin(event));
	}
	
	public void loadAdminMain() throws IOException{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/AdminMain.fxml"));
		pnRoot.getChildren().setAll(pane);
	}

}
