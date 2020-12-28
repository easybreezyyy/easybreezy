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

/** 사용자 버전 컨트롤러 */
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
	

	// --------------------------DAO 클래스에서 정보 가져와서 초기화 된 상태------------------------//
	public static ArrayList<ItemVO> allItems = new ArrayList<>(); // 전체 상품 리스트
	public static ArrayList<ItemVO> allItems2 = new ArrayList<>(); // 재고순으로 담은 상품리스트 - 메인 화면에 보여질 상품리스트
	public static ArrayList<ItemVO> allItems3 = new ArrayList<>(); // 브랜드명으로 검색한 상품들 담은 리스트
	
	//테이블 연동을 위한 리스트
	public static ObservableList<RentalTableVO> rentalList = FXCollections.observableArrayList();

	/** 현재 로그인한 멤버 정보를 세팅해주는 메서드 */
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
	private ImageView arrMain[] = new ImageView[6]; // 메인화면에 보여질 이미지뷰 배열

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

    //-------------------------------------------상단 각 버튼 클릭 시 화면 이동-------------------------------------------------//
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
			System.out.println("메인 패널 로딩 실패");
			e.printStackTrace();
		}
	}
	
	
	//----------------------------------------상품 관련 메서드--------------------------------------------//

	/** 상품 검색  -> search() */
	public void handleSearch(ActionEvent event) {
		search();
	}

    /** 상품 검색 후 엔터 처리  -> search() */
    public void handleEnter(KeyEvent event) {
    	if (event.getCode() == KeyCode.ENTER)
    		search();            
    	else
    		return;
    }

	/** 상품 검색 메서드 */
	public void search() {
		String brand = tfSearch.getText().trim().toUpperCase();
		if (brand.length() == 0)
			return;
		itemdao.searchByBrand(brand);
		if (allItems3.size() != 0) {
			pagination.setPageCount(allItems3.size());
			pagination.setPageFactory((index) -> createSearchPage(index));
		} else {
			String alert = "검색 결과가 없습니다.\n다른 브랜드명을 검색해주세요!";
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
    
	/** 메인에 있는 상품 이미지 클릭시 바로 주문 가능한 퀵메뉴 구현 메서드 */
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
    
	/** 상품 클릭시 주문창 활성화 */
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
	
	/** 상품 주문시 */
	public void handleOrder(ActionEvent event) {
		rental = new RentalVO();
		rental.setAddress(member.getAddr());	rental.setId(member.getId());
		rental.setName(member.getName());	rental.setPhone(member.getPhone());
		rental.setStatus("준비중");	rental.setStylenum(item.getStylenum());
		rental.setRentaldate(today);	rental.setReturndate(returnDate);
		int i = rentaldao.insertData(rental);
		int k = rentaldao.curRentalnum();
		System.out.println("rentalnum 확인 : " + k);
		
		rt = new ReturnVO();
		rt.setAddress(member.getAddr());	rt.setId(member.getId());
		rt.setName(member.getName());	rt.setPhone(member.getPhone());
		rt.setStylenum(item.getStylenum()); 	rt.setRentaldate(today);
		rt.setReturndate(returnDate);	rt.setStatus("미수거");
		rt.setRentalnum(k);
		int j = returndao.insertData(rt);
		
		memberdao.updateStatus("준비중", member.getPhone());
		
		System.out.println(i + "행 추가를 Customer Controller에서 확인");
		System.out.println(j + "행 추가를 Customer Controller에서 확인");
		pnOrder.setVisible(false);
		
		int s = item.getStock();	System.out.println(s);	//DB 재고 처리
		item.setStock(--s);			System.out.println(s);
		itemdao.rentItem(item);
		
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXButton button = new JFXButton("OK");
		JFXDialog dialog = new JFXDialog(pnStack, dialogLayout, JFXDialog.DialogTransition.TOP);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{dialog.close();});
		
		Text text = new Text("주문 완료!");
		text.setFont(Font.font("Malgun Gothic"));
		dialogLayout.setBody(text);
		dialogLayout.setActions(button);
		dialog.show();
	}
	
	/** 상품 주문 취소시 */
	public void handleCancelOrder(ActionEvent event) {
		pnOrder.setVisible(false);
	}
	
	
	
	//---------------------------------------회원 관련 메서드---------------------------------------------//
	
	/** 회원 탈퇴 - 언스크라입 버튼 눌렀을 때 */
	public void handleUnsubscribe(ActionEvent event) {
		pnSure.setVisible(true);
	}

	public void handleSureToUnsubscribe(ActionEvent event) {
		int i = memberdao.deleteMember(member.getId());
		System.out.println("성공적으로 탈퇴되었습니다 : " + i);

		String alert = "성공적으로 탈퇴되었습니다.\n확인을 누르시면 메인화면으로 돌아갑니다.";
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXButton button = new JFXButton("OK");
		JFXDialog dialog = new JFXDialog(pnStack, dialogLayout, JFXDialog.DialogTransition.TOP);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
			dialog.close();
			try {
				loadMain();
			} catch (IOException e) {
				System.out.println("메인 패널 로딩 실패");
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

	/** 회원정보 수정 - 업데이트 버튼 눌렀을 때 */
	@FXML
	void handleUpdate(ActionEvent event) {
		String newPassword = tfAccountPassword.getText().trim();
		String alert = "성공적으로 수정되었습니다.";
		if (newPassword.length() != 0 && newPassword.equals(tfAccountConfirmpwd.getText().trim())) {
			String newAddr = tfAccountAddr.getText();
			String newPhone = tfAccountPhone.getText().trim();
			String newCard = tfAccountPhone.getText().trim();

			member.setAddr(newAddr);
			member.setCard(newCard);
			member.setPassword(newPassword);
			member.setPhone(newPhone);

			int i = memberdao.updateMember(member);
			System.out.println("customerController에서 확인 : " + i);

		} else
			alert = "비밀번호를 제대로 입력했는지 확인해주세요.";

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

	
	//--------------------------------------------기초 화면 구성------------------------------------------//
	
	/** 상품검색시 보여질 페이지네이션 세팅 메서드 */
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

	/** 기본 페이지네이션 세팅 메서드 */
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

	/** 로그아웃시 메인화면 로딩 메서드 */
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
