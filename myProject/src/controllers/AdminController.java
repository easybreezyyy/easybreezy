package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class AdminController implements Initializable {

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
	private TableView<?> tbCustomers;

	@FXML
	private JFXButton btDeleteMember;

	@FXML
	private AnchorPane pnRecent;

	@FXML
	private JFXButton btSms;

	@FXML
	private AnchorPane pnItems;

	@FXML
	private JFXListView<?> lsItems;

	@FXML
	private JFXTextField tfStylenum;

	@FXML
	private JFXTextField tfItemname;

	@FXML
	private JFXTextField tfBrand;

	@FXML
	private JFXTextField tfStock;

	@FXML
	private JFXTextField tfPrice;

	@FXML
	private JFXButton btSave;

	@FXML
	private JFXButton btDeleteItem;

	@FXML
	private JFXButton btLoad;

	@FXML
	private AnchorPane pnAdminHome;

	@FXML
	private JFXButton btRepresentNewCustomer;

	@FXML
	void handleCustomer(ActionEvent event) {
		pnAdminHome.toBack();
		pnItems.toBack();
		pnRecent.toBack();
		pnCustomer.toFront();
	}

	@FXML
	void handleDeleteMember(ActionEvent event) {

	}

	@FXML
	void handleHome(ActionEvent event) {

	}

	@FXML
	void handleItems(ActionEvent event) {

	}

	@FXML
	void handleLogout(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/application/LogoutDialog.fxml"));
			DialogPane LogoutDialogPane = fxmlLoader.load();
			
			AdminController adminController = fxmlLoader.getController();
			//adminController.setMember(member);
			
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setDialogPane(LogoutDialogPane);
			dialog.setTitle("Logout");
			
			Optional<ButtonType> result = dialog.showAndWait();
			if(result.get()==ButtonType.YES) {
				System.out.println("로그아웃 하시겠답니다.");
			}else if (result.get()==ButtonType.CANCEL) {
				System.out.println("로그아웃 취소");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void handleRecent(ActionEvent event) {

	}

	@FXML
	void handleSms(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btCustomer.setOnAction(event -> handleCustomer(event));
		btHome.setOnAction(event -> handleHome(event));
		btRepresentNewCustomer.setOnAction(event -> handleCustomer(event));
		btItems.setOnAction(event -> handleItems(event));
	}

}
