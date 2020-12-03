package application;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class SampleController implements Initializable {

	// ----------------Member-----------------//
	private Member member = new Member();
	private Map<String, Member> map = new HashMap<>();

	@FXML
	private AnchorPane signinpn;

	@FXML
	private Button signinbt;

	@FXML
	private Button registerbt;

	@FXML
	private AnchorPane registerpn;

	@FXML
	private JFXComboBox<String> storecb;
	private ObservableList<String> storeList = 
			FXCollections.observableArrayList("Seogyo", "Dosan", "Seongsu", "Hannam","Headquater");

	@FXML
	private JFXComboBox<String> positioncb;
	private ObservableList<String> positionList = FXCollections.observableArrayList("Staff","Manager","Admin");

	@FXML
	private JFXTextField phonetf;

	@FXML
	private JFXButton backtosigninbt;

	@FXML
	private JFXButton registerbt2;

	@FXML
	private JFXTextField usernametf;

	@FXML
	private JFXPasswordField passwordtf;

	@FXML
	private JFXPasswordField confirmPwdtf;

	@FXML
	public void handleRegister(ActionEvent event) {
		signinpn.setVisible(false);
		registerpn.setVisible(true);
		usernametf.requestFocus();
	}

	public void handleRegister2(ActionEvent event) {
		// String username = usernametf.getText();
		// String store = storecb.getSelectionModel().getSelectedItem();
		// String password = passwordtf.getText();
		// String comfirmPassword = confirmPwdtf.getText();

	}

	@FXML
	public void handleSignin(ActionEvent event) {

	}

	@FXML
	public void handleback(ActionEvent event) {
		registerpn.setVisible(false);
		signinpn.setVisible(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		positioncb.setItems(positionList);
		storecb.setItems(storeList);
		signinbt.setOnAction(event -> handleSignin(event));
		registerbt.setOnAction(event -> handleRegister(event));
		backtosigninbt.setOnAction(event -> handleback(event));
		registerbt2.setOnAction(event -> handleRegister2(event));

	}

	public void insert(Member member) {
		map.put(member.getUsername(), member);
	}

	public void delete(Member member) {
		map.remove(member.getUsername());
	}

}
