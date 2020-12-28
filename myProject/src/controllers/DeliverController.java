package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import application.CollectTableVO;
import application.DeliveryTableVO;
import application.MemberDAO;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DeliverController implements Initializable{
	
	MemberDAO memberdao = new MemberDAO();
	RentalDAO rentaldao = new RentalDAO();
	ReturnDAO returndao = new ReturnDAO();
	String s = "��� ������";
	String str = "���� ������";

	
	//-----------------------------------���̺� ������ ���� ����Ʈ--------------------------------------//
	public static ObservableList<DeliveryTableVO> deliveryList = FXCollections.observableArrayList();
	public static ObservableList<CollectTableVO> collectList = FXCollections.observableArrayList();

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
    private JFXComboBox<String> cbState1;

    @FXML
    private TableView<CollectTableVO> tbCollect;
    
    @FXML
    private TableColumn<CollectTableVO, String> c_col_name;

    @FXML
    private TableColumn<CollectTableVO, String> c_col_addr;

    @FXML
    private TableColumn<CollectTableVO, String> c_col_phone;

    @FXML
    private TableColumn<CollectTableVO, String> c_col_stylenum;

    @FXML
    private JFXButton btCompleteCollect;

    @FXML
    private VBox pnDelivery;

    @FXML
    private JFXComboBox<String> cbState;

    @FXML
    private TableView<DeliveryTableVO> tbDelivery;

    @FXML
    private TableColumn<DeliveryTableVO, String> col_name;

    @FXML
    private TableColumn<DeliveryTableVO, String> col_addr;

    @FXML
    private TableColumn<DeliveryTableVO, String> col_phone;

    @FXML
    private TableColumn<DeliveryTableVO, String> col_stylenum;
    
    @FXML
    private JFXButton btCompleteDelivery;

    //--------------------------------------�� �޴� Ŭ���� ȭ�� �̵�-------------------------------------------------//
  
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

    //------------------------------------------- �������� / ���� ���� ��� ----------------------------------------------//
    
    /**
     * �޺��ڽ����� ���� ���ý� �ش� ���� ����Ʈ ����
     */
    @FXML
    void getState(ActionEvent event) {
    	deliveryList.clear();
    	s = cbState.getSelectionModel().getSelectedItem().toString();
    	rentaldao.getDeliveryTable(s);
    	tbDelivery.setItems(deliveryList);
    } 
    
    @FXML
    void getState1(ActionEvent event) {
    	collectList.clear();
    	str = cbState1.getSelectionModel().getSelectedItem().toString();
    	System.out.println(str);
    	returndao.getCollectTable(str);
    	tbCollect.setItems(collectList);
    } 

    /** Complete ��ư ���ý� - ��� �Ϸ�� */
    @FXML
    void handleCompleteDelivery(ActionEvent event) {
    	DeliveryTableVO dt = tbDelivery.getSelectionModel().getSelectedItem();
    	int no = dt.getRentalnum();
    	String phone = dt.getPhone();
    	String status = "�뿩��";
    	rentaldao.updateStatus(status, no);
    	memberdao.updateStatus(status, phone);
    	
    	deliveryList.clear();
    	rentaldao.getDeliveryTable(s);
    	
    	JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXButton button = new JFXButton("OK");
		JFXDialog dialog = new JFXDialog(pnStack, dialogLayout, JFXDialog.DialogTransition.TOP);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{dialog.close();});
		
		Text text = new Text("�Ϸ�!\n����� ���ΰ�ħ�մϴ�.");
		text.setFont(Font.font("Malgun Gothic"));
		dialogLayout.setBody(text);
		dialogLayout.setActions(button);
		dialog.show();
    }
    

    /** Complete ��ư ���ý� - ���� �Ϸ�� */
    @FXML
    void handleCompleteCollect(ActionEvent event) {
    	CollectTableVO ct = tbCollect.getSelectionModel().getSelectedItem();
    	String phone = ct.getPhone();
    	int no = ct.getRentalnum();
    	memberdao.updateStatus("-", phone);
    	rentaldao.deleteData(no);
    	
    	collectList.clear();
    	returndao.getCollectTable(str);
    	
    	JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXButton button = new JFXButton("OK");
		JFXDialog dialog = new JFXDialog(pnStack, dialogLayout, JFXDialog.DialogTransition.TOP);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{dialog.close();});
		
		Text text = new Text("�Ϸ�!\n����� ���ΰ�ħ�մϴ�.");
		text.setFont(Font.font("Malgun Gothic"));
		dialogLayout.setBody(text);
		dialogLayout.setActions(button);
		dialog.show();
    }
    
    
    //----------------------------------------�⺻ ȭ�� ����------------------------------------------------//
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btLogout.setOnAction(event->handleLogout(event));
		btCollect.setOnAction(event->handleCollect(event));
		btDelivery.setOnAction(event->handleDelivery(event));
		btCompleteCollect.setOnAction(event->handleCompleteCollect(event));
		btCompleteDelivery.setOnAction(event->handleCompleteDelivery(event));
		cbState.setOnAction(event->getState(event));
		cbState1.setOnAction(event->getState1(event));
		
		ObservableList<String> stateList = FXCollections.observableArrayList("������","��õ��","���α�",
		"��õ��","���۱�","��������","���Ǳ�","���ʱ�","������","���ı�","������","������","������","�߱�",
		"��걸","������","���빮��","���α�","����","���ϱ�","���ϱ�","�����","������","���빮��","�߶���");
		cbState.setItems(stateList);
		cbState1.setItems(stateList);
		
		col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_addr.setCellValueFactory(new PropertyValueFactory<>("address"));
		col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		col_stylenum.setCellValueFactory(new PropertyValueFactory<>("stylenum"));
		tbDelivery.setItems(deliveryList);
		c_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		c_col_addr.setCellValueFactory(new PropertyValueFactory<>("address"));
		c_col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		c_col_stylenum.setCellValueFactory(new PropertyValueFactory<>("stylenum"));
		tbCollect.setItems(collectList);
		
		
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
