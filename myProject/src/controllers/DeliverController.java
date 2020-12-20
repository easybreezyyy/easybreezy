package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DeliverController implements Initializable{

    @FXML
    private AnchorPane pnRoot;

    @FXML
    private JFXButton btDelivery;

    @FXML
    private JFXButton btCollect;

    @FXML
    private JFXButton btLogout;

    @FXML
    private StackPane pnStack;

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

    
    /**
     * �޺��ڽ����� ���� ���ý� �ش� ���� ����Ʈ ����
     */
    @FXML
    void getState(ActionEvent event) {

    }

    @FXML
    void handleCompleteCollect(ActionEvent event) {

    }

    @FXML
    void handleCompleteDelivery(ActionEvent event) {

    }
    
    @FXML
    void handleCollect(ActionEvent event) {
    	pnCollect.setVisible(true);
    	pnDelivery.setVisible(false);
    }

    @FXML
    void handleDelivery(ActionEvent event) {
    	pnCollect.setVisible(false);
    	pnDelivery.setVisible(true);
    }

    @FXML
    void handleLogout(ActionEvent event) {
    	try {
			loadMain();
		} catch (IOException e) {
			System.out.println("���� �г� �ε� ����");
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btLogout.setOnAction(event->handleLogout(event));
		btCollect.setOnAction(event->handleCollect(event));
		btDelivery.setOnAction(event->handleDelivery(event));
		
		
	}

	public void loadMain() throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		pnRoot.getChildren().setAll(pane);
	}
	
}
