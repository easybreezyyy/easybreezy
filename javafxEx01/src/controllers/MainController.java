package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;



public class MainController implements Initializable {

	@FXML
	private JFXButton studentsbt;

	@FXML
	private JFXButton teachersbt;

	@FXML
	private JFXButton feesbt;

	@FXML
	private JFXButton usersbt;

	@FXML
	private JFXButton settingsbt;

	@FXML
	private Pane statuspn;

	@FXML
	private Label statuslb;

	@FXML
	private Label statuspathlb;

	@FXML
	private FontAwesomeIconView closebt;

	@FXML
    private GridPane teacherspn;

    @FXML
    private GridPane feespn;

    @FXML
    private GridPane userspn;

    @FXML
    private GridPane studentspn;

    @FXML
    private GridPane settingspn;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		studentsbt.setOnAction(event->handleClicks(event));
		teachersbt.setOnAction(event->handleClicks(event));
		feesbt.setOnAction(event->handleClicks(event));
		usersbt.setOnAction(event->handleClicks(event));
		settingsbt.setOnAction(event->handleClicks(event));
		closebt.setOnMouseClicked(event->handleClose(event));
		
	}
	
	public void handleClose(MouseEvent event) {
		System.exit(0);
		return;
	}

	@FXML
	public void handleClicks(ActionEvent event) {
		if(event.getSource()==studentsbt) {
			statuspathlb.setText("/home/students");
			statuslb.setText("Students");
			studentspn.toFront();
			return;
		}
		if(event.getSource()==teachersbt) {
			statuspathlb.setText("/home/teachers");
			statuslb.setText("Teachers");
			teacherspn.toFront();
			return;
		}
		if(event.getSource()==feesbt) {
			statuspathlb.setText("/home/fees");
			statuslb.setText("Fees");
			feespn.toFront();
			return;
		}
		if(event.getSource()==usersbt) {
			statuspathlb.setText("/home/users");
			statuslb.setText("Users");
			userspn.toFront();
			return;
		}
		if(event.getSource()==settingsbt) {
			statuspathlb.setText("/home/settings");
			statuslb.setText("Settings");
			settingspn.toFront();
			return;
		}
	}
	
	
	
}
