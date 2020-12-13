package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class DeliverController implements Initializable {

	@FXML
	private JFXButton btDelivery;

	@FXML
	private JFXButton btCollect;

	@FXML
	private JFXButton btLogout;

	@FXML
	private VBox pnCollect;

	@FXML
	private JFXComboBox<?> cbState1;

	@FXML
	private TableView<?> tbCollect;

	@FXML
	private JFXButton btCompleteCollect;

	@FXML
	private VBox pnDelivery;

	@FXML
	private JFXComboBox<?> cbState;

	@FXML
	private TableView<?> tbDelivery;

	@FXML
	private JFXButton btCompleteDelivery;

	@FXML
	void getState(ActionEvent event) {

	}

	@FXML
	void handleCollect(ActionEvent event) {

	}

	@FXML
	void handleCompleteCollect(ActionEvent event) {

	}

	@FXML
	void handleCompleteDelivery(ActionEvent event) {

	}

	@FXML
	void handleDelivery(ActionEvent event) {

	}

	@FXML
	void handleLogout(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
