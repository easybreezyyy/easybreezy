package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class AdminController implements Initializable {

    @FXML
    private StackPane pnAdminRoot;
	
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
    private TableView<?> tbCustomers;
	
	@FXML
	void handleCustomer(ActionEvent event) {
		pnCustomer.toFront();
	}

	@FXML
	void handleDeleteMember(ActionEvent event) {

	}

	@FXML
	void handleHome(ActionEvent event) {
		pnCustomer.toBack();
		pnAdminHome.toFront();
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
		btRepresentNewCustomer.setOnAction(event-> handleCustomer(event));
		btItems.setOnAction(event -> handleItems(event));
	}

	
	
}
