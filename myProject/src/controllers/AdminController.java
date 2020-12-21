package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import application.CustomerTableVO;
import application.MemberDAO;
import application.MemberVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class AdminController implements Initializable {

	MemberDAO memberdao = new MemberDAO();
	MemberVO member;
	
	public static ObservableList<CustomerTableVO> customerList = FXCollections.observableArrayList();

	@FXML StackPane pnStack;
	
	@FXML
	private AnchorPane pnRoot;

	@FXML
	private TableView<CustomerTableVO> tbCustomers;

	@FXML
	private TableColumn<CustomerTableVO, String> colId;

	@FXML
	private TableColumn<CustomerTableVO, String> colName;

	@FXML
	private TableColumn<CustomerTableVO, String> colPhone;

	@FXML
	private TableColumn<CustomerTableVO, String> colCard;

	@FXML
	private TableColumn<CustomerTableVO, String> colStatus;

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
	
	@FXML private ImageView img; 

	public void handleCustomer(ActionEvent event) {
		pnAdminHome.setVisible(false);
		pnItems.setVisible(false);
		pnRecent.setVisible(false);
		pnCustomer.setVisible(true);
	}

	public void handleHome(ActionEvent event) {
		pnItems.setVisible(false);
		pnRecent.setVisible(false);
		pnCustomer.setVisible(false);
		pnAdminHome.setVisible(true);
	}

	public void handleItems(ActionEvent event) {
		pnAdminHome.setVisible(false);
		pnRecent.setVisible(false);
		pnCustomer.setVisible(false);
		pnItems.setVisible(true);
	}

	public void handleRecent(ActionEvent event) {
		pnAdminHome.setVisible(false);
		pnItems.setVisible(false);
		pnCustomer.setVisible(false);
		pnRecent.setVisible(true);
	}

	public void handleLogout(ActionEvent event) throws IOException {
		tbCustomers.getItems().clear();
		loadMain();
	}

	/** 멤버 삭제 */
	@FXML void handleDeleteMember(ActionEvent event) {
		String alert = "성공적으로 삭제되었습니다.";
		CustomerTableVO ct = tbCustomers.getSelectionModel().getSelectedItem();
		if(ct != null) {
			tbCustomers.getItems().remove(ct);
			memberdao.deleteMember(ct.getId());
			
			JFXDialogLayout dialogLayout = new JFXDialogLayout();
			JFXButton button = new JFXButton("OK");
			JFXDialog dialog = new JFXDialog(pnStack, dialogLayout, JFXDialog.DialogTransition.TOP);
			button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{dialog.close();});
			
			Text text = new Text(alert);
			text.setFont(Font.font("Malgun Gothic"));
			dialogLayout.setBody(text);
			dialogLayout.setActions(button);
			dialog.show();
		}
	}

	
	/** 이미지 파일 업로드 */
	public void fileChoose(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("JPG Files", "*.jpg"));
		File f = fc.showOpenDialog(null);
		if(f!=null) {
			//파일이 잘 선택되었다면 이미지뷰에 보이게 하고 -> DONE
			// db에 저장시키기
			System.out.println(f.getAbsolutePath());
			String url = "file:\\" + f.getAbsolutePath();
			Image image = new Image(url);
			img.setImage(image);
			img.setSmooth(true);
		}
	}
	
	
	
	/** 문자 보내기 (CoolSMS) */
	@FXML void handleSms(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		memberdao.getCustomerTable();
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colCard.setCellValueFactory(new PropertyValueFactory<>("card"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tbCustomers.setItems(customerList);
		
		btRepresentNewCustomer.setText(String.valueOf(memberdao.todayMember()));
		btCustomer.setOnAction(event -> handleCustomer(event));
		btHome.setOnAction(event -> handleHome(event));
		btRepresentNewCustomer.setOnAction(event -> handleCustomer(event));
		btItems.setOnAction(event -> handleItems(event));
		btLoad.setOnAction(event-> fileChoose(event));
	}



	public void loadMain() throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		pnRoot.getChildren().setAll(pane);
	}
}
