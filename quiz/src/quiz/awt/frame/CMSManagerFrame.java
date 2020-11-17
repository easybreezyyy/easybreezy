package quiz.awt.frame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;

public class CMSManagerFrame extends Frame {

	private static final long serialVersionUID = 1L;
	
	//-----------------MENU------------------------//
	private MenuBar mb = new MenuBar();
	private Menu file = new Menu("File");
		private MenuItem fnew = new MenuItem("New File");
		private MenuItem fopen = new MenuItem("File Open");
		private MenuItem fsave = new MenuItem("File Save");
		private MenuItem fsaveas = new MenuItem("File Save As");
		private MenuItem exit = new MenuItem("Exit");
	private Menu help = new Menu("Help");
		private MenuItem info = new MenuItem("Version Info");
	
	private TextField tf1 = new TextField(20);
	private TextField tf2 = new TextField(7);
	private TextField tf3 = new TextField(8);
	private TextField tf4 = new TextField(4);
	private TextArea ta = new TextArea(10,20);
	
	private java.awt.List li = new java.awt.List(12);	//java.util List랑 헷갈릴까봐 생성할 때 한번 이렇게 써줘야한다.
	private Choice cho = new Choice();
	private String[] num = {"010", "017", "011"};
	private Label bar = new Label("-",Label.CENTER);
	
	private CheckboxGroup cg = new CheckboxGroup();
		private Checkbox man = new Checkbox("남성", cg, true);
		private Checkbox woman = new Checkbox("여성", cg, false);
	
	private String[] hobby = {"독서","영화","음악","게임","쇼핑"};
	private Checkbox[] cb = new Checkbox[hobby.length];
	
	private Button addbt = new Button("등록");
	private Button analbt = new Button("분석");
	private Button editbt = new Button("수정");
	private Button delbt = new Button("삭제");
	private Button initbt = new Button("입력모드");
	private Label lb1 = new Label("개인정보 입력", Label.CENTER);
	private Label lb2 = new Label("전 체 목 록", Label.CENTER);
	private Label lb3 = new Label("개 인 정 보 분 석", Label.CENTER);
	private Label glb1= new Label("이      름 :", Label.CENTER);
	private Label glb2 = new Label("주민번호 :", Label.CENTER);
	private Label glb3 = new Label("전화번호 :", Label.CENTER);
	private Label glb4 = new Label("성      별 :", Label.CENTER);
	private Label glb5 = new Label("취      미 :", Label.CENTER);

	//------------------------Dialog-------------------------------//
	private Dialog dialog = new Dialog(this,"버전 정보",true);
	private Label dlabel = new Label("Customer Manager Version 1.0",Label.CENTER);
	private Button dbt = new Button("확인");
	
	
	public CMSManagerFrame() {
		super("Customer Manager");
		setDialog();
		setMenu();
		buildGUI();
	}
	
	public void setButton(boolean state) {
		addbt.setEnabled(state);
		analbt.setEnabled(!state);
		editbt.setEnabled(!state);
		delbt.setEnabled(!state);
		initbt.setEnabled(!state);
	}
	
	/**
	 * 메뉴 구성
	 */
	public void setMenu() {
		setMenuBar(mb);
		mb.add(file);	mb.add(help);
		file.add(fnew);	file.addSeparator();
		file.add(fopen); file.add(fsave); file.add(fsaveas); file.addSeparator();
		file.add(exit);
		help.add(info);
	}
	
	
	/**
	 * 다이얼로그 구성
	 */
	private void setDialog() {
		dialog.setLayout(new BorderLayout(3,3));
		dialog.add("North",new Label());
		dialog.add("South",new Label());
		dialog.add("West",new Label());
		dialog.add("East",new Label());
		
		Panel dCenter = new Panel(new GridLayout(2,1,3,3));
			dlabel.setFont(new Font("Arial",Font.BOLD,15));
			dCenter.add(dlabel);
			Panel dsPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
			dsPanel.add(dbt);
		dCenter.add(dsPanel);
		dialog.add(dCenter);
		
		dialog.pack();
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension my = dialog.getSize();
		dialog.setLocation(scr.width/2-my.width/2, scr.height/2-my.height/2);
		dialog.setVisible(true);
	}

	/**
	 *화면구성
	 */
	private void buildGUI() {

		setLayout(new BorderLayout(3,3));		// 패널 간 여백의 미...ㅎ
		setBackground(Color.cyan);

		add("North",new Label());	add("South",new Label());	//여백의 미^^...
		add("West",new Label());	add("East",new Label());
	
		Panel pinput = new Panel(new BorderLayout(3,3));		//개인정보입력 패널
		pinput.add("North", lb1);
			
		Panel pgrid = new Panel(new GridLayout(5,1,3,3));		
			pgrid.add(glb1);	pgrid.add(glb2); 	pgrid.add(glb3);
			pgrid.add(glb4); 	pgrid.add(glb5);
		pinput.add("West",pgrid);
		
		Panel pgrid2 = new Panel(new GridLayout(5,1,3,3));
		Panel pname = new Panel(new FlowLayout(FlowLayout.LEFT));
			pname.add(tf1);
			pgrid2.add(pname);	
		
		Panel pid = new Panel(new FlowLayout(FlowLayout.LEFT));
			pid.add(tf2); 	pid.add(new Label("-"));	pid.add(tf3);
			pgrid2.add(pid);
		
		Panel pnum = new Panel(new FlowLayout(FlowLayout.LEFT));
			for(String temp : num)
			cho.add(temp);
			pnum.add(cho);	pnum.add(bar);	pnum.add(tf4);	pnum.add(new Label("-"));	pnum.add(new TextField(4));
			pgrid2.add(pnum);
		
		Panel psex = new Panel(new FlowLayout(FlowLayout.LEFT));
			psex.add(man);	psex.add(woman);
			pgrid2.add(psex);
		
		Panel phobby = new Panel(new FlowLayout(FlowLayout.LEFT));	
			for(int i = 0; i < cb.length; i++) {
				cb[i] = new Checkbox(hobby[i],false);
				phobby.add(cb[i]);
			}
			pgrid2.add(phobby);
		pinput.add("Center",pgrid2);
		
		Panel pbt = new Panel(new FlowLayout(FlowLayout.CENTER));
			pbt.add(addbt);	pbt.add(analbt);	pbt.add(editbt);	pbt.add(delbt);	pbt.add(initbt);
			pinput.add("South", pbt);
		
		Panel plist = new Panel(new BorderLayout(3,3));		//전체목록 패널
			plist.add("North", lb2);	plist.add("Center",li);
		
		Panel panal = new Panel(new BorderLayout(3,3));		//개인정보분석 패널
			panal.add("North",lb3);		panal.add("Center",ta);
		
		Panel wrapper = new Panel(new BorderLayout());		//패널 전체를 담을 레이아웃
			wrapper.add("Center",pinput);
			wrapper.add("East",plist);
			wrapper.add("South",panal);
		add("Center",wrapper);
		
		pack();
		
		//set Location at center
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension my = getSize();
		setLocation(scr.width/2-my.width/2, scr.height/2-my.height/2);
		
		setButton(true);
		setResizable(false);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new CMSManagerFrame();
	}

}
