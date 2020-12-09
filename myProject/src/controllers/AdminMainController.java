package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class AdminMainController implements Initializable {

	@FXML
	private JFXButton btItems;

	@FXML
	private JFXButton btCustomer;

	@FXML
	private JFXButton btRecent;

	@FXML
	private JFXButton btLogout;

	@FXML
	private JFXButton btHome;

	@FXML
	private AnchorPane pnCustomer;

	@FXML
	private JFXButton btDeleteMember;

	@FXML
	private AnchorPane pnAdminHome;

	@FXML
	private JFXButton btRepresentNewCustomer;

	@FXML
	void handleCustomer(ActionEvent event) {
		pnAdminHome.setVisible(false);
		pnCustomer.setVisible(true);
	}

	@FXML
	void handleDeleteMember(ActionEvent event) {

	}

	@FXML
	void handleHome(ActionEvent event) {
		pnAdminHome.setVisible(true);
	}

	@FXML
	void handleItems(ActionEvent event) {

	}

	@FXML
	void handleLogout(ActionEvent event) {

	}

	@FXML
	void handleRecent(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btCustomer.setOnAction(event -> handleCustomer(event));
		btHome.setOnAction(event -> handleHome(event));
	}

}
