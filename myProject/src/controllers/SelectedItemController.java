package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class SelectedItemController implements Initializable {

	@FXML
	private AnchorPane pnOrder2;

	@FXML
	private Label lbItemname;

	@FXML
	private Label lbRentalDate;

	@FXML
	private Label lbReturnDate;

	@FXML
	private Label lbRentalTimes;

	@FXML
	private JFXButton btComplete;

	@FXML
	private JFXButton btBack;

	@FXML
	private HBox pnOrder1;

	@FXML
	private ImageView imageSelectedItem;

	@FXML
	private JFXButton btOrder;

	@FXML
	private JFXTextArea taDescription;

	@FXML
	void handleBack(ActionEvent event) {
		pnOrder1.toFront();
	}

	@FXML
	void handleComplete(ActionEvent event) {

	}

	@FXML
	void handleOrder(ActionEvent event) {
		//pnOrder1.setVisible(false);
		pnOrder2.toFront();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btOrder.setOnAction(event->handleOrder(event));
		btBack.setOnAction(event->handleBack(event));

	}

}
