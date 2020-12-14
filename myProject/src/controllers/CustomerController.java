package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CustomerController implements Initializable {

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
	private JFXTextField tfAccountPassword;

	@FXML
	private JFXTextField tfAccountPhone;

	@FXML
	private JFXTextField tfAccountConfirmpwd;

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
	void handleCloset(ActionEvent event) {
		
	}

	@FXML
	void handleHome(ActionEvent event) {

	}

	@FXML
	void handleItems(ActionEvent event) {
		pnItems.toFront();
	}

	@FXML
	void handleLogout(ActionEvent event) {
		
	}

	@FXML
	void handleMyAccount(ActionEvent event) {

	}

	@FXML
	void handleSearch(ActionEvent event) {

	}

	@FXML
	void handleUnsubscribe(ActionEvent event) {

	}

	@FXML
	void handleUpdate(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btItems.setOnAction(event->handleItems(event));
		

	}

}
