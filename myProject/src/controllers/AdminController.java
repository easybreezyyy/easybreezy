package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
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
	
	//-----------------------------------���̺�� ����Ʈ ������ ���� ����Ʈ--------------------------------------//
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
    private TableColumn<RecentTableVO, Integer> col_rentalnumber;

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
    private Label lbRecent;

    @FXML
    private JFXButton btSms;

    @FXML
    private JFXComboBox<String> cbRecent;
	
	//-------------------------------------��� ��ư ������ �� ȭ�� �̵�---------------------------------------//
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
		rentaldao.getRecentTable();
		tbRecent.setItems(recentList);
	}

	public void handleLogout(ActionEvent event) throws IOException {
		lsItems.getItems().clear();
		tbCustomers.getItems().clear();
		loadMain();
	}
	
	//--------------------------------------ȸ�� ���� ���� �޼���--------------------------------------------//

	/** ��� ���� */
	@FXML 
	void handleDeleteMember(ActionEvent event) {
		String alert = "���������� �����Ǿ����ϴ�.";
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
	
	/** ���� ������ (CoolSMS) */
	@FXML void handleSms(ActionEvent event) {

	}

	
	//-----------------------------------------��ǰ ���� �޼���------------------------------------------//
	
	/** �̹��� ���� ���ε� */
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
	 * ��ǰ ���� �߰��ϴ� �޼���
	 */
	public void handleSave(ActionEvent event) {
		String alert = "��ǰ ������ ��Ȯ�� �Է��ߴ��� Ȯ�����ּ���."; 
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
			alert = "�̹� �����ϴ� ǰ���Դϴ�.\n �ٸ� ǰ���� ������ּ���.";
			flag = false;
		}
		
		if(flag==true) {
			item = new ItemVO(stylenum,itemname,brand,stock,url,price);
			itemdao.insertItem(item);
			itemList.add(item);
			alert = "���������� ����Ǿ����ϴ�.";
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
	 * ��ǰ ���� �����ϴ� �޼���
	 */
	public void handleUpdate(ActionEvent event) {
		String alert = "��ǰ ������ ��Ȯ�� �Է��ߴ��� Ȯ�����ּ���."; 
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
			alert = "���������� ����Ǿ����ϴ�.";
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
	 * ��ǰ ���� �޼���
	 */
	@FXML
	void handleDeleteItem(ActionEvent event) {
		String stylenum = tfStylenum.getText().trim();
		String alert = "���������� �����Ǿ����ϴ�.";
		
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
	 * ����Ʈ ��ǰ ���ý�
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

	
	//------------------------------------------�뿩 ���� �޼���--------------------------------------------------//
	
	/** Recent���� �޺��ڽ� ���ý� ������ ���̺��� �����ϴ� �޼���*/
	 @FXML
	 void select(ActionEvent event) {
		 recentList.clear();
		 String s = cbRecent.getSelectionModel().getSelectedItem().toString();
		 if(s.equals("�ֹ�")) {
			lbRecent.setText("���� �ֹ� ���");
			rentaldao.getRecentTable();
			 return;
		 }
		 if(s.equals("����")) {
			 lbRecent.setText("���� ���� ���");
			 returndao.getReturnTable();
			 return;
		 }
		 if(s.equals("�̼���")) {
			 lbRecent.setText("���� �̼��� ���");
			 
			 return;
		 }
		 
		 tbRecent.setItems(recentList);
	    }
	
	
	
	//----------------------------------------�⺻ ȭ�� ����------------------------------------------------------//

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
		
		ObservableList<String> todayList = FXCollections.observableArrayList("�ֹ�","����","�̼���");
		cbRecent.setItems(todayList);
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_rentalnumber.setCellValueFactory(new PropertyValueFactory<>("rentalnumber"));
		col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
		col_stylenum.setCellValueFactory(new PropertyValueFactory<>("stylenum"));
		//tbRecent.setItems(recentList);
		
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
