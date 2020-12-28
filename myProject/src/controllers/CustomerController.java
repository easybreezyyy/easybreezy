package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.ItemDAO;
import application.ItemVO;
import application.MemberDAO;
import application.MemberVO;
import application.RentalDAO;
import application.RentalTableVO;
import application.RentalVO;
import application.ReturnDAO;
import application.ReturnVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

/** ����� ���� ��Ʈ�ѷ� */
public class CustomerController implements Initializable {

	MemberDAO memberdao = new MemberDAO();
	MemberVO member;

	ItemDAO itemdao = new ItemDAO();
	ItemVO item;
	
	RentalDAO rentaldao = new RentalDAO();
	RentalVO rental;
	
	ReturnDAO returndao = new ReturnDAO();
	ReturnVO rt;

	Image image;
	
	Date today = new Date();
	Date returnDate;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Calendar cal = Calendar.getInstance();
	

	// --------------------------DAO Ŭ�������� ���� �����ͼ� �ʱ�ȭ �� ����------------------------//
	public static ArrayList<ItemVO> allItems = new ArrayList<>(); // ��ü ��ǰ ����Ʈ
	public static ArrayList<ItemVO> allItems2 = new ArrayList<>(); // �������� ���� ��ǰ����Ʈ - ���� ȭ�鿡 ������ ��ǰ����Ʈ
	public static ArrayList<ItemVO> allItems3 = new ArrayList<>(); // �귣������� �˻��� ��ǰ�� ���� ����Ʈ
	
	//���̺� ������ ���� ����Ʈ
	public static ObservableList<RentalTableVO> rentalList = FXCollections.observableArrayList();

	/** ���� �α����� ��� ������ �������ִ� �޼��� */
	public void setUser(String id) {
		this.member = memberdao.getMember(id);
		this.welcome.setText("Welcome to Our Closet, " + id);
		System.out.println(member.toString());
	}
	
	@FXML
	private AnchorPane pnRoot;

	@FXML
	private Text welcome;

	@FXML
	private JFXButton btItems;

	@FXML
	private JFXButton btCloset;

	@FXML
	private JFXButton btMyAccount;

	@FXML
	private JFXButton btLogout;

	@FXML
	private JFXButton btHome;

	@FXML
	private VBox pnItems;

	@FXML
	private JFXTextField tfSearch;

	@FXML
	private JFXButton btsearch;

	@FXML
	private AnchorPane pnCloset;

	@FXML
	private Label lbCountRemain;

	@FXML
	private AnchorPane pnMyAccount;

	@FXML
	private JFXPasswordField tfAccountPassword;

	@FXML
	private JFXTextField tfAccountPhone;

	@FXML
	private JFXPasswordField tfAccountConfirmpwd;

	@FXML
	private JFXTextField tfAccountAddr;

	@FXML
	private JFXTextField tfAccountCard;

	@FXML
	private Label lbAccountId;

	@FXML
	private Label lbAccountName;

	@FXML
	private JFXButton btUpdate;

	@FXML
	private JFXButton btUnsubscribe;

	@FXML
	private Label lbCompleteUpdate;

	@FXML
	private AnchorPane pnHome;

	@FXML
	private StackPane pnStack;

	@FXML
	private AnchorPane pnSure;

	@FXML
	private JFXButton btCancel;

	@FXML
	private JFXButton btSureToUnsubscribe;

	@FXML
	private GridPane pnGrid;

	@FXML
	private ImageView mainItem0;

	@FXML
	private ImageView mainItem1;

	@FXML
	private ImageView mainItem2;

	@FXML
	private ImageView mainItem3;

	@FXML
	private ImageView mainItem4;

	@FXML
	private ImageView mainItem5;

	@FXML
	private ImageView arrMain[] = new ImageView[6]; // ����ȭ�鿡 ������ �̹����� �迭

	@FXML
	private Button btGotoItem;

	@FXML
	private Pagination pagination;
	
	@FXML private JFXButton btn;

    @FXML
    private AnchorPane pnOrder;

    @FXML
    private Label lbItemname;

    @FXML
    private Label lbRentalDate;

    @FXML
    private Label lbReturnDate;

    @FXML
    private Label lbRentalTimes;

    @FXML
    private Label lbBrand;

    @FXML
    private JFXButton btOrder;

    @FXML
    private JFXButton btCancelOrder;
    
    @FXML
    private TableView<RentalTableVO> tbCloset;

    @FXML
    private TableColumn<RentalTableVO, Integer> col_rn;

    @FXML
    private TableColumn<RentalTableVO, Date> col_rentalDate;

    @FXML
    private TableColumn<RentalTableVO, Date> col_returnDate;

    @FXML
    private TableColumn<RentalTableVO, String> col_itemName;

    @FXML
    private TableColumn<RentalTableVO, String> col_brand;

    @FXML
    private TableColumn<RentalTableVO, String> col_status;

    //-------------------------------------------��� �� ��ư Ŭ�� �� ȭ�� �̵�-------------------------------------------------//
	public void handleCloset(ActionEvent event) {
		rentalList.clear();
		rentaldao.getRentalTable(member.getId());
		tbCloset.setItems(rentalList);
		pnOrder.setVisible(false);
		pnHome.setVisible(false);
		pnItems.setVisible(false);
		pnMyAccount.setVisible(false);
		pnCloset.setVisible(true);
	}

	@FXML
	void handleHome(ActionEvent event) {
		pnOrder.setVisible(false);
		pnItems.setVisible(false);
		pnMyAccount.setVisible(false);
		pnCloset.setVisible(false);
		pnHome.setVisible(true);
	}

	@FXML
	void handleItems(ActionEvent event) {
		pnOrder.setVisible(false);
		pnHome.setVisible(false);
		pnMyAccount.setVisible(false);
		pnCloset.setVisible(false);
		pnItems.setVisible(true);
		tfSearch.setText("");
		tfSearch.requestFocus();
		pagination.setPageCount(allItems.size());
		pagination.setPageFactory((index) -> createPage(index));
	}
	
	@FXML
	void handleMyAccount(ActionEvent event) {
		pnOrder.setVisible(false);
		pnHome.setVisible(false);
		pnItems.setVisible(false);
		pnCloset.setVisible(false);
		pnMyAccount.setVisible(true);
		lbAccountId.setText(member.getId());
		lbAccountName.setText(member.getName());
		tfAccountAddr.setText(member.getAddr());
		tfAccountCard.setText(member.getCard());
		tfAccountPhone.setText(member.getPhone());
		tfAccountPassword.setText("");
		tfAccountConfirmpwd.setText("");
	}

	public void handleLogout(ActionEvent event) {
		try {
			loadMain();
		} catch (IOException e) {
			System.out.println("���� �г� �ε� ����");
			e.printStackTrace();
		}
	}
	
	
	//----------------------------------------��ǰ ���� �޼���--------------------------------------------//

	/** ��ǰ �˻�  -> search() */
	public void handleSearch(ActionEvent event) {
		search();
	}

    /** ��ǰ �˻� �� ���� ó��  -> search() */
    public void handleEnter(KeyEvent event) {
    	if (event.getCode() == KeyCode.ENTER)
    		search();            
    	else
    		return;
    }

	/** ��ǰ �˻� �޼��� */
	public void search() {
		String brand = tfSearch.getText().trim().toUpperCase();
		if (brand.length() == 0)
			return;
		itemdao.searchByBrand(brand);
		if (allItems3.size() != 0) {
			pagination.setPageCount(allItems3.size());
			pagination.setPageFactory((index) -> createSearchPage(index));
		} else {
			String alert = "�˻� ����� �����ϴ�.\n�ٸ� �귣����� �˻����ּ���!";
			JFXDialogLayout dialogLayout = new JFXDialogLayout();
			JFXButton button = new JFXButton("OK");
			JFXDialog dialog = new JFXDialog(pnStack, dialogLayout, JFXDialog.DialogTransition.TOP);
			button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {dialog.close();});

			Text text = new Text(alert);
			text.setFont(Font.font("Malgun Gothic"));
			dialogLayout.setBody(text);
			dialogLayout.setActions(button);
			dialog.show();
		}
		
	}
    
	/** ���ο� �ִ� ��ǰ �̹��� Ŭ���� �ٷ� �ֹ� ������ ���޴� ���� �޼��� */
	public void mouseClicked(MouseEvent e) {
		
		for(int i = 0; i <arrMain.length; i++) {
			if(e.getSource()==arrMain[i]) {
				item = allItems2.get(i);
				returnDate = new Date();
				pnOrder.setVisible(true);
				pnOrder.toFront();
				lbBrand.setText(item.getBrand());
				lbItemname.setText(item.getItemname());
				lbRentalDate.setText(sdf.format(today));
		        cal.setTime(returnDate);
		        cal.add(Calendar.DATE, 7);
				lbReturnDate.setText(sdf.format(cal.getTime()));
				returnDate = cal.getTime();
			}
		}
		
	}
    
	/** ��ǰ Ŭ���� �ֹ�â Ȱ��ȭ */
	public void goToItem(ActionEvent event) {
		returnDate = new Date();
		pnOrder.setVisible(true);
		lbBrand.setText(item.getBrand());
		lbItemname.setText(item.getItemname());
		lbRentalDate.setText(sdf.format(today));
        cal.setTime(returnDate);
        cal.add(Calendar.DATE, 7);
		lbReturnDate.setText(sdf.format(cal.getTime()));
		returnDate = cal.getTime();
		System.out.println(returnDate);
	}
	
	/** ��ǰ �ֹ��� */
	public void handleOrder(ActionEvent event) {
		rental = new RentalVO();
		rental.setAddress(member.getAddr());	rental.setId(member.getId());
		rental.setName(member.getName());	rental.setPhone(member.getPhone());
		rental.setStatus("�غ���");	rental.setStylenum(item.getStylenum());
		rental.setRentaldate(today);	rental.setReturndate(returnDate);
		int i = rentaldao.insertData(rental);
		int k = rentaldao.curRentalnum();
		System.out.println("rentalnum Ȯ�� : " + k);
		
		rt = new ReturnVO();
		rt.setAddress(member.getAddr());	rt.setId(member.getId());
		rt.setName(member.getName());	rt.setPhone(member.getPhone());
		rt.setStylenum(item.getStylenum()); 	rt.setRentaldate(today);
		rt.setReturndate(returnDate);	rt.setStatus("�̼���");
		rt.setRentalnum(k);
		int j = returndao.insertData(rt);
		
		memberdao.updateStatus("�غ���", member.getPhone());
		
		System.out.println(i + "�� �߰��� Customer Controller���� Ȯ��");
		System.out.println(j + "�� �߰��� Customer Controller���� Ȯ��");
		pnOrder.setVisible(false);
		
		int s = item.getStock();	System.out.println(s);	//DB ��� ó��
		item.setStock(--s);			System.out.println(s);
		itemdao.rentItem(item);
		
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXButton button = new JFXButton("OK");
		JFXDialog dialog = new JFXDialog(pnStack, dialogLayout, JFXDialog.DialogTransition.TOP);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{dialog.close();});
		
		Text text = new Text("�ֹ� �Ϸ�!");
		text.setFont(Font.font("Malgun Gothic"));
		dialogLayout.setBody(text);
		dialogLayout.setActions(button);
		dialog.show();
	}
	
	/** ��ǰ �ֹ� ��ҽ� */
	public void handleCancelOrder(ActionEvent event) {
		pnOrder.setVisible(false);
	}
	
	
	
	//---------------------------------------ȸ�� ���� �޼���---------------------------------------------//
	
	/** ȸ�� Ż�� - ��ũ���� ��ư ������ �� */
	public void handleUnsubscribe(ActionEvent event) {
		pnSure.setVisible(true);
	}

	public void handleSureToUnsubscribe(ActionEvent event) {
		int i = memberdao.deleteMember(member.getId());
		System.out.println("���������� Ż��Ǿ����ϴ� : " + i);

		String alert = "���������� Ż��Ǿ����ϴ�.\nȮ���� �����ø� ����ȭ������ ���ư��ϴ�.";
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXButton button = new JFXButton("OK");
		JFXDialog dialog = new JFXDialog(pnStack, dialogLayout, JFXDialog.DialogTransition.TOP);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
			dialog.close();
			try {
				loadMain();
			} catch (IOException e) {
				System.out.println("���� �г� �ε� ����");
				e.printStackTrace();
			}
		});

		Text text = new Text(alert);
		text.setFont(Font.font("Malgun Gothic"));
		dialogLayout.setBody(text);
		dialogLayout.setActions(button);
		dialog.show();
	}

	public void handleCancel(ActionEvent event) {
		pnSure.setVisible(false);
	}

	/** ȸ������ ���� - ������Ʈ ��ư ������ �� */
	@FXML
	void handleUpdate(ActionEvent event) {
		String newPassword = tfAccountPassword.getText().trim();
		String alert = "���������� �����Ǿ����ϴ�.";
		if (newPassword.length() != 0 && newPassword.equals(tfAccountConfirmpwd.getText().trim())) {
			String newAddr = tfAccountAddr.getText();
			String newPhone = tfAccountPhone.getText().trim();
			String newCard = tfAccountPhone.getText().trim();

			member.setAddr(newAddr);
			member.setCard(newCard);
			member.setPassword(newPassword);
			member.setPhone(newPhone);

			int i = memberdao.updateMember(member);
			System.out.println("customerController���� Ȯ�� : " + i);

		} else
			alert = "��й�ȣ�� ����� �Է��ߴ��� Ȯ�����ּ���.";

		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXButton button = new JFXButton("OK");
		JFXDialog dialog = new JFXDialog(pnStack, dialogLayout, JFXDialog.DialogTransition.TOP);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
			dialog.close();
		});

		Text text = new Text(alert);
		text.setFont(Font.font("Malgun Gothic"));
		dialogLayout.setBody(text);
		dialogLayout.setActions(button);
		dialog.show();
	}

	
	//--------------------------------------------���� ȭ�� ����------------------------------------------//
	
	/** ��ǰ�˻��� ������ ���������̼� ���� �޼��� */
	public BorderPane createSearchPage(int index) {
		btn = new JFXButton();
		ImageView img = new ImageView();

		item = allItems3.get(index);

		String brand = item.getBrand();
		String itemname = item.getItemname();
		Text text = new Text(brand + "\n" + itemname);
		String url = item.getImagepath();
		Image image = new Image(url);
		img.setImage(image);
		img.setFitHeight(400);
		img.setFitWidth(400);
		img.setPreserveRatio(true);
		img.setSmooth(true);
		img.setCache(true);

		btn.setGraphic(img);
		btn.setPrefHeight(400);
		btn.setPrefWidth(400);
		btn.setOnAction(event -> goToItem(event));

		BorderPane p = new BorderPane();
		p.setCenter(btn);
		p.setBottom(text);
		BorderPane.setAlignment(text, Pos.CENTER);
		return p;
		
	}

	/** �⺻ ���������̼� ���� �޼��� */
	public BorderPane createPage(int index) {
		btn = new JFXButton();
		ImageView img = new ImageView();

		item = allItems.get(index);

		String brand = item.getBrand();
		String itemname = item.getItemname();
		Text text = new Text(brand + "\n" + itemname);
		String url = item.getImagepath();
		Image image = new Image(url);
		img.setImage(image);
		img.setFitHeight(400);
		img.setFitWidth(400);
		img.setPreserveRatio(true);
		img.setSmooth(true);
		img.setCache(true);

		btn.setGraphic(img);
		btn.setPrefHeight(400);
		btn.setPrefWidth(400);
		btn.setOnAction(event -> goToItem(event));

		BorderPane p = new BorderPane();
		p.setCenter(btn);
		p.setBottom(text);
		BorderPane.setAlignment(text, Pos.CENTER);
		return p;
	}

	/** �α׾ƿ��� ����ȭ�� �ε� �޼��� */
	public void loadMain() throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		pnRoot.getChildren().setAll(pane);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setUser(MainController.id());
		
		col_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
		col_itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		col_rentalDate.setCellValueFactory(new PropertyValueFactory<>("rentalDate"));
		col_returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
		col_rn.setCellValueFactory(new PropertyValueFactory<>("rentalnum"));
		col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		itemdao.setItemList();
		itemdao.orderByStock();

		arrMain[0] = mainItem0;	arrMain[1] = mainItem1;
		arrMain[2] = mainItem2;	arrMain[3] = mainItem3;
		arrMain[4] = mainItem4;	arrMain[5] = mainItem5;

		for (int i = 0; i < 6; i++) {
			String url = allItems2.get(i).getImagepath();
			Image image = new Image(url);
			arrMain[i].setImage(image);
		}

		tfSearch.setOnKeyPressed(event->handleEnter(event));
		btHome.setOnAction(event -> handleHome(event));
		btItems.setOnAction(event -> handleItems(event));
		btCloset.setOnAction(event -> handleCloset(event));
		btMyAccount.setOnAction(event -> handleMyAccount(event));
		btLogout.setOnAction(event -> handleLogout(event));
		btsearch.setOnAction(event -> handleSearch(event));
		btUpdate.setOnAction(event -> handleUpdate(event));
		btUnsubscribe.setOnAction(event -> handleUnsubscribe(event));
		btSureToUnsubscribe.setOnAction(event -> handleSureToUnsubscribe(event));
		btCancel.setOnAction(event -> handleCancel(event));
		btOrder.setOnAction(event->handleOrder(event));
		btCancelOrder.setOnAction(event->handleCancelOrder(event));
		mainItem0.setOnMouseClicked(event -> mouseClicked(event));
		mainItem1.setOnMouseClicked(event -> mouseClicked(event));
		mainItem2.setOnMouseClicked(event -> mouseClicked(event));
		mainItem3.setOnMouseClicked(event -> mouseClicked(event));
		mainItem4.setOnMouseClicked(event -> mouseClicked(event));
		mainItem5.setOnMouseClicked(event -> mouseClicked(event));
	

		// this code will create a page and load inside pagination control
		pagination.setPageCount(allItems.size());
		pagination.setPageFactory(new Callback<Integer, Node>() {
			@Override
			public Node call(Integer index) {
				return createPage(index);
			}
		});
		
	}// end of initialize

}
