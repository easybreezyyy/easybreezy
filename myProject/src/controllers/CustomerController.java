package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.ItemDAO;
import application.ItemVO;
import application.MemberDAO;
import application.MemberVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class CustomerController implements Initializable {

	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	Connection con = null;
	PreparedStatement pstmt = null;

	MemberDAO memberdao = new MemberDAO();
	MemberVO member = null;
	Map<String, MemberVO> map;

	ItemVO item;
	ItemDAO itemdao = new ItemDAO();

	Image image;

	// -------------------DAO 클래스에서 정보 가져와서 초기화 된 상태------------------------//
	public static ArrayList<ItemVO> allItems = new ArrayList<>(); // 전체 상품 리스트
	public static ArrayList<ItemVO> allItems2 = new ArrayList<>(); // 재고순으로 담은 상품리스트

	/**
	 * 현재 로그인한 멤버 정보를 세팅해주는 메서드
	 */
	public void setUser(String id) {
		this.member = memberdao.getMember(id);
		this.welcome.setText("WELCOME " + id);
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
	GridPane pnGrid;

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

	public void handleCloset(ActionEvent event) {
		pnHome.setVisible(false);
		pnItems.setVisible(false);
		pnMyAccount.setVisible(false);
		pnCloset.setVisible(true);
		return;
	}

	@FXML
	void handleHome(ActionEvent event) {
		pnItems.setVisible(false);
		pnMyAccount.setVisible(false);
		pnCloset.setVisible(false);
		pnHome.setVisible(true);
		return;
	}

	@FXML
	void handleItems(ActionEvent event) {
		pnHome.setVisible(false);
		pnMyAccount.setVisible(false);
		pnCloset.setVisible(false);
		pnItems.setVisible(true);

	}


	@FXML
	void handleMyAccount(ActionEvent event) {
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
		return;
	}

	public void handleLogout(ActionEvent event) {
		try {
			loadMain();
		} catch (IOException e) {
			System.out.println("메인 패널 로딩 실패");
			e.printStackTrace();
		}
	}

	/**
	 * 상품 검색
	 */
	@FXML
	void handleSearch(ActionEvent event) {
		
	}

	/**
	 * 회원 탈퇴 - 언스크라입 버튼 눌렀을 때
	 */
	@FXML
	void handleUnsubscribe(ActionEvent event) {
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

	/**
	 * 회원정보 수정 - 업데이트 버튼 눌렀을 때
	 */
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

	// 나중에 하자 - how to detect hover

	@FXML
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == mainItem0) {
			Text text = new Text();
			String brand = allItems2.get(0).getBrand();
			text.setText(brand);
			text.setVisible(true);
			return;
		}
		if (e.getSource() == mainItem1) {
			Text text = new Text();
			String brand = allItems2.get(1).getBrand();
			text.setText(brand);
			return;
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		itemdao.setItemList();
		itemdao.orderByStock();
		setUser(MainController.id());

		arrMain[0] = mainItem0;
		arrMain[1] = mainItem1;
		arrMain[2] = mainItem2;
		arrMain[3] = mainItem3;
		arrMain[4] = mainItem4;
		arrMain[5] = mainItem5;

		for (int i = 0; i < 6; i++) {
			String url = allItems2.get(i).getImagepath();
			Image image = new Image(url);
			arrMain[i].setImage(image);
		}

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
		mainItem0.setOnMouseEntered(event -> mouseEntered(event));
		// mainItem1.setOnMouseEntered(event->mouseEntered(event));

		// this code will create a page and load inside pagination control
		pagination.setPageCount(allItems.size());
		pagination.setPageFactory(new Callback<Integer, Node>() {

			@Override
			public Node call(Integer index) {
				return createPage(index);
			}

		});

		
	}


	public BorderPane createPage(int index) {
		ImageView img = new ImageView();

		ItemVO item = new ItemVO();
		item = allItems.get(index);
		
		String brand = item.getBrand();
		String itemname = item.getItemname();
		Text text = new Text(brand+"\n"+itemname);
		String url = item.getImagepath();
		Image image = new Image(url);
		img.setImage(image);
		img.setFitHeight(400);
		img.setFitWidth(400);
		img.setPreserveRatio(true);

		img.setSmooth(true);
		img.setCache(true);

		
		BorderPane p = new BorderPane();
		p.setCenter(img);
		p.setBottom(text);
		BorderPane.setAlignment(text, Pos.CENTER);
		return p;
	}
	
	public void loadMain() throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		pnRoot.getChildren().setAll(pane);
	}

}
