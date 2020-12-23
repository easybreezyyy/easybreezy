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
import application.ItemDAO;
import application.ItemVO;
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
	ItemDAO itemdao = new ItemDAO();
	ItemVO item;
	String url = "/image/blank.png";
	Image image = new Image(url);
	
	public static ObservableList<CustomerTableVO> customerList = FXCollections.observableArrayList();
	public static ObservableList<ItemVO> itemList = FXCollections.observableArrayList();
	

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
	private JFXListView<ItemVO> lsItems;

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
	
	@FXML private JFXButton btUpdate;
	
	@FXML private ImageView img; 
	
	@FXML private JFXButton btNew; 

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
		btSave.setVisible(true);
		btUpdate.setVisible(false);
		tfBrand.setText("");
		tfItemname.setText("");
		tfPrice.setText("");
		tfStock.setText("");
		tfStylenum.setText("");
		img.setImage(new Image("/image/blank.png"));
		tfStylenum.setEditable(true);
		btNew.setVisible(false);
	}

	public void handleRecent(ActionEvent event) {
		pnAdminHome.setVisible(false);
		pnItems.setVisible(false);
		pnCustomer.setVisible(false);
		pnRecent.setVisible(true);
	}

	public void handleLogout(ActionEvent event) throws IOException {
		lsItems.getItems().clear();
		tbCustomers.getItems().clear();
		loadMain();
	}

	/** 멤버 삭제 */
	@FXML 
	void handleDeleteMember(ActionEvent event) {
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
			url = "file:\\" + f.getAbsolutePath();
			Image image = new Image(url);
			img.setImage(image);
			img.setSmooth(true);
			this.item.setImagepath(url);
		}
	}
	
	/**
	 * 상품 정보 추가하는 메서드
	 */
	public void handleSave(ActionEvent event) {
		String alert = "상품 정보를 정확히 입력했는지 확인해주세요."; 
		Boolean flag = true;
		
		String stylenum = tfStylenum.getText().trim();
		if(stylenum.length()==0) flag=false;
		String itemname = tfItemname.getText().trim();
		if(itemname.length()==0) flag=false;
		String brand = tfBrand.getText().trim();
		if(brand.length()==0) flag=false;
		
		int stock = 0;
		if(tfStock.getText().trim().length()==0) 
			flag = false;
			else
				stock = Integer.parseInt(tfStock.getText().trim());
		
		int price = 0;
		if(tfPrice.getText().trim().length()==0) 
			flag = false;
			else
				price = Integer.parseInt(tfPrice.getText().trim());	
		
		if(itemdao.checkDuplicate(stylenum)!=0) {
			alert = "이미 존재하는 품번입니다.\n 다른 품번을 사용해주세요.";
			flag = false;
		}
		
		if(flag==true) {
			item = new ItemVO(stylenum,itemname,brand,stock,url,price);
			itemdao.insertItem(item);
			itemList.add(item);
			alert = "성공적으로 저장되었습니다.";
		}
		
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXButton button = new JFXButton("OK");
		JFXDialog dialog = new JFXDialog(pnStack, dialogLayout, JFXDialog.DialogTransition.TOP);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{dialog.close();});
		
		Text text = new Text(alert);
		text.setFont(Font.font("Malgun Gothic"));
		dialogLayout.setBody(text);
		dialogLayout.setActions(button);
		dialog.show();
		
		btNew.setVisible(true);
		
	}
	
	/**
	 * 상품 정보 수정하는 메서드
	 */
	public void handleUpdate(ActionEvent event) {
		String alert = "상품 정보를 정확히 입력했는지 확인해주세요."; 
		Boolean flag = true;
		
		String stylenum = tfStylenum.getText().trim();
		if(stylenum.length()==0) flag=false;
		String itemname = tfItemname.getText().trim();
		if(itemname.length()==0) flag=false;
		String brand = tfBrand.getText().trim();
		if(brand.length()==0) flag=false;
		int stock = 0;
		stock = Integer.parseInt(tfStock.getText().trim());
		int price = 0;
		price = Integer.parseInt(tfPrice.getText().trim());
		
		if(flag==true) {
			item = new ItemVO(stylenum,itemname,brand,stock,url,price);
			itemdao.updateItem(item);
			alert = "성공적으로 저장되었습니다.";
		}
		
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXButton button = new JFXButton("OK");
		JFXDialog dialog = new JFXDialog(pnStack, dialogLayout, JFXDialog.DialogTransition.TOP);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{dialog.close();});
		
		Text text = new Text(alert);
		text.setFont(Font.font("Malgun Gothic"));
		dialogLayout.setBody(text);
		dialogLayout.setActions(button);
		dialog.show();
		
		btNew.setVisible(true);
		itemList.clear();
		itemdao.setItemList();
		lsItems.setItems(itemList);
	}
	
	/**
	 * 상품 삭제 메서드
	 */
	@FXML
	void handleDeleteItem(ActionEvent event) {
		String stylenum = tfStylenum.getText().trim();
		String alert = "성공적으로 삭제되었습니다.";
		
		itemdao.deleteItem(stylenum);
		int k = itemList.indexOf(itemdao.getItem(stylenum));
		itemList.remove(k);
		
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

	/**
	 * 리스트 상품 선택시
	 */
	@FXML
    void clickedItem(MouseEvent event) {
		this.item = lsItems.getSelectionModel().getSelectedItem();
		
		tfStylenum.setText(item.getStylenum());
		tfStylenum.setEditable(false);
		tfItemname.setText(item.getItemname());
		tfBrand.setText(item.getBrand());
		tfStock.setText(String.valueOf(item.getStock()));
		tfPrice.setText(String.valueOf(item.getPrice()));
		url = item.getImagepath();
		image = new Image(url);
		img.setImage(image);
		
		btSave.setVisible(false);
		btUpdate.setVisible(true);
		btNew.setVisible(true);
    }

	
	/** 문자 보내기 (CoolSMS) */
	@FXML void handleSms(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		itemdao.setItemList();
		memberdao.getCustomerTable();
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colCard.setCellValueFactory(new PropertyValueFactory<>("card"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tbCustomers.setItems(customerList);
		lsItems.setItems(itemList);
		
		btRepresentNewCustomer.setText(String.valueOf(memberdao.todayMember()));
		btCustomer.setOnAction(event -> handleCustomer(event));
		btHome.setOnAction(event -> handleHome(event));
		btRepresentNewCustomer.setOnAction(event -> handleCustomer(event));
		btItems.setOnAction(event -> handleItems(event));
		btLoad.setOnAction(event-> fileChoose(event));
		btSave.setOnAction(event->handleSave(event));
		btDeleteItem.setOnAction(event->handleDeleteItem(event));
		btUpdate.setOnAction(event->handleUpdate(event));
		btNew.setOnAction(event->handleItems(event));
	}


	public void loadMain() throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		pnRoot.getChildren().setAll(pane);
	}
}
