package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import application.CustomerTableVO;
import application.ItemDAO;
import application.ItemVO;
import application.MemberDAO;
import application.MemberVO;
import application.RecentTableVO;
import application.RentalDAO;
import application.ReturnDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import javafx.stage.Stage;

public class AdminController implements Initializable {

	MemberDAO memberdao = new MemberDAO();
	MemberVO member;
	ItemDAO itemdao = new ItemDAO();
	ItemVO item;
	RentalDAO rentaldao = new RentalDAO();
	ReturnDAO returndao = new ReturnDAO();
	String url = "/image/blank.png";
	Image image = new Image(url);
	
	//-----------------------------------테이블과 리스트 세팅을 위한 리스트--------------------------------------//
	public static ObservableList<CustomerTableVO> customerList = FXCollections.observableArrayList();
	public static ObservableList<RecentTableVO> recentList = FXCollections.observableArrayList();
	public static ObservableList<ItemVO> itemList = FXCollections.observableArrayList();
	

	@FXML private StackPane pnStack;
	
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

	@FXML
    private TableView<RecentTableVO> tbRecent;

    @FXML
    private TableColumn<RecentTableVO, Integer> col_rentalnum;

    @FXML
    private TableColumn<RecentTableVO, String> col_name;

    @FXML
    private TableColumn<RecentTableVO, String> col_id;

    @FXML
    private TableColumn<RecentTableVO, String> col_stylenum;

    @FXML
    private TableColumn<RecentTableVO, String> col_status;

    @FXML
    private TableColumn<RecentTableVO, String> col_address;
    
    @FXML
    private TableColumn<RecentTableVO, Integer> col_returnnum;
    
    @FXML
    private TableColumn<RecentTableVO, String> col_returndate;

    @FXML
    private Label lbRecent;

    @FXML
    private JFXButton btSms;

    @FXML
    private JFXComboBox<String> cbRecent;
	
    @FXML
    private AnchorPane pnSms;

    @FXML
    private JFXTextArea taSms;

    @FXML
    private JFXButton btSend;
    
    
	//-------------------------------------상단 버튼 눌렀을 때 화면 이동---------------------------------------//
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
		recentList.clear();
		pnAdminHome.setVisible(false);
		pnItems.setVisible(false);
		pnCustomer.setVisible(false);
		pnRecent.setVisible(true);
		cbRecent.getSelectionModel().select(0);
		tbRecent.setItems(recentList);
	}

	public void handleLogout(ActionEvent event) throws IOException {
		lsItems.getItems().clear();
		tbCustomers.getItems().clear();
		loadMain();
	}
	
	//--------------------------------------회원 관리 관련 메서드--------------------------------------------//

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
	
	/** 문자 보내기 (CoolSMS) */
	@FXML void goToSms(ActionEvent event) {
		pnSms.setVisible(true);
	}
	
	@FXML
	public void handleSend(ActionEvent event) {
		String text = taSms.getText();
		CustomerTableVO ct = tbCustomers.getSelectionModel().getSelectedItem();
		String phone = ct.getPhone();
		System.out.println(phone);
		String alert = "발송 완료하였습니다.";
		
		if(phone!=null) {
			String api_key = "##";
		    String api_secret = "##";
		    Message coolsms = new Message(api_key, api_secret);
	
		    // 4 params(to, from, type, text) are mandatory. must be filled
		    HashMap<String, String> params = new HashMap<String, String>();
		    params.put("to", phone);
		    params.put("from", "01058740013");
		    params.put("type", "SMS");
		    params.put("text", text);
		    params.put("app_version", "test app 1.2"); // application name and version
	
		    try {
		      JSONObject obj = (JSONObject) coolsms.send(params);
		      System.out.println(obj.toString());
		      
		    } catch (CoolsmsException e) {
		      System.out.println(e.getMessage());
		      System.out.println(e.getCode());
		    }
		}else
			alert = "문자 발송에 실패하였습니다.\n 고객 핸드폰번호 또는 문자 글자수를(45자 내) 확인해주세요.";
		
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXButton button = new JFXButton("OK");
		JFXDialog dialog = new JFXDialog(pnStack, dialogLayout, JFXDialog.DialogTransition.TOP);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{dialog.close();});
		
		Text tt = new Text(alert);
		tt.setFont(Font.font("Malgun Gothic"));
		dialogLayout.setBody(tt);
		dialogLayout.setActions(button);
		dialog.show();
	    pnSms.setVisible(false);
		
	}

	
	//-----------------------------------------상품 관련 메서드------------------------------------------//
	
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

	
	//------------------------------------------대여 관련 메서드--------------------------------------------------//
	
	/** Recent에서 콤보박스 선택시 보여질 테이블을 구성하는 메서드*/
	 @FXML
	 void select(ActionEvent event) {
		 recentList.clear();
		 String s = cbRecent.getSelectionModel().getSelectedItem().toString();
		 if(s.equals("주문")) {
			lbRecent.setText("금일 주문 목록");
			rentaldao.getRecentTable();
			col_returndate.setVisible(false);
			col_returnnum.setVisible(false);
			return;
		 }
		 if(s.equals("수거")) {
			 lbRecent.setText("금일 수거 목록");
			 returndao.getReturnTable();
			 col_returndate.setVisible(true);
			 col_returnnum.setVisible(true);
			 return;
		 }
		 if(s.equals("연체")) {
			 lbRecent.setText("금일 연체 목록");
			 returndao.getOverdueTable();		 
			 col_returndate.setVisible(true);
			 col_returnnum.setVisible(true);
			 return;
		 }
		 tbRecent.setItems(recentList);
	    }
	
	
	
	//----------------------------------------기본 화면 구성------------------------------------------------------//

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
		
		ObservableList<String> todayList = FXCollections.observableArrayList("주문","수거","연체");
		cbRecent.setItems(todayList);
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_rentalnum.setCellValueFactory(new PropertyValueFactory<>("rentalnum"));
		col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
		col_stylenum.setCellValueFactory(new PropertyValueFactory<>("stylenum"));
		col_returnnum.setCellValueFactory(new PropertyValueFactory<>("returnnum"));
		col_returndate.setCellValueFactory(new PropertyValueFactory<>("returndate"));
		
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
		cbRecent.setOnAction(event->select(event));
		btSms.setOnAction(event->goToSms(event));
		btSend.setOnAction(event->handleSend(event));
	}


	public void loadMain() throws IOException {
		try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/application/Main.fxml"));
				Parent root = loader.load();
				controllers.MainController con = loader.getController();
				Scene scene = new Scene(root);
				Stage stage = (Stage) btLogout.getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
