package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class SampleController implements Initializable{

	@FXML private Button loginbt;
	@FXML private AnchorPane loginpn;
	@FXML private AnchorPane welcomepn;
	@FXML private Button gotItbt;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginbt.setOnAction(event->login(event));
		gotItbt.setOnAction(event->gotIt(event));
	}

	public void gotIt(ActionEvent event) {
		welcomepn.setVisible(false);
	}

	public void login(ActionEvent event) {
		loginpn.setVisible(false);
	}
	
}
