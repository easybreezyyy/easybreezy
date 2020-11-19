package quiz.awt.event01;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class CMSManager extends Frame implements FocusListener, ItemListener, ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	
	//---------------Member info-------------------//
		private MemberVO member = new MemberVO();
		private Map<String, MemberVO> map = new HashMap<>();
		
		
	
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
		
		private TextField tf1 = new TextField(20);			//이름 입력창
		private TextField tf2 = new TextField(6);			//생년월일 입력창
		private TextField tf3 = new TextField(7);			//주민 뒷번호 입력창
		private TextField tf4 = new TextField(4);			//핸드폰 중간 번호 입력창
		private TextField tf5 = new TextField(4);			//핸드폰 뒷번호 입력창
		private TextArea ta = new TextArea(10,20);			//분석창
		
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
		
		
		//--------------생성자---------------//
		public CMSManager() {
			super("Customer Manager event ver.");
			setEvent();
			setDialog();
			setMenu();
			buildGUI();
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		}
		
		//멤버 추가
		private void insert(MemberVO member) {
			map.put(member.getName(), member);
		}
		
		//화면 초기화
		private void cls() {
			tf1.setText(""); tf2.setText("");
			tf3.setText(""); tf4.setText("");
			tf5.setText("");
			
			cho.select(0);
			man.setState(true);
			for(int i=0; i<hobby.length; i++)
		        cb[i].setState(false);
			ta.setText("");
			tf1.requestFocus();
		}
		
		private void setEditable(boolean state) {
			tf1.setEditable(state);
			tf2.setEditable(state);
			tf3.setEditable(state);
			man.setEnabled(state); 		woman.setEnabled(state);
		}

		private void setEvent() {
			tf1.addActionListener(this);
			tf1.addKeyListener(this);
			tf2.addKeyListener(this);
			tf3.addKeyListener(this);
			tf4.addKeyListener(this);
			tf5.addKeyListener(this);
			addbt.addActionListener(this);
			editbt.addActionListener(this);
			analbt.addActionListener(this);
			delbt.addActionListener(this);
			initbt.addActionListener(this);
			li.addItemListener(this);
			
			
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
			
			
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					dialog.setVisible(false);
				}
			});
			
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
				pnum.add(cho);	pnum.add(bar);	pnum.add(tf4);	pnum.add(new Label("-"));	pnum.add(tf5);
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
			
			Panel wrapper = new Panel(new BorderLayout(3,3));		//패널 전체를 담을 레이아웃
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
			//setResizable(false);
			setVisible(true);
			
		}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==tf1)			
			tf2.requestFocus();
		
		//등록버튼 눌렀을 때
		if(e.getSource()==addbt) {
			String name = tf1.getText().trim();
			if(name.trim().length()==0) return;
			String id = tf2.getText() + tf3.getText();
			if(id.trim().length()==0) return;
			String num1 = cho.getSelectedItem();
			String num2 = tf4.getText().trim();
			if(num2.trim().length()==0) return;
			String num3 = tf5.getText().trim();
			if(num3.trim().length()==0) return;
			String gender = cg.getSelectedCheckbox().getLabel();
			String hb = "";
			for(int i=0; i<hobby.length; i++) {
				if(cb[i].getState()) {
					hb += cb[i].getLabel() + "/";
				}
			}
			if(hb.length()!=0) {
				hb = hb.substring(0, hb.length()-1);		//맨마지막의  / 제거
			}else {
				hb = "없음";
			}
			
			MemberVO member = new MemberVO(name,id,num1,num2,num3,gender,hb);
			insert(member);
			System.out.println(member.toString());
			
			li.add(tf1.getText().trim());
			cls();
			ta.setText("\n\t\t성공적으로 등록 되었습니다.");
			return;
		}
		
		//분석 버튼
		if(e.getSource()==analbt) {
			
			String memberinfo = ""; 		//ta에 띄울 문자열
			String id1 = tf2.getText().trim();
	        String id2 = tf3.getText().trim();
	        int[] id = new int[13];
	        for(int i=0; i<id.length; i++) {
	           if(i < 6)
	              id[i] = id1.charAt(i) - 48;
	           else
	              id[i] = id2.charAt(i-6) - '0';
	        }
	        
	        double j = 2.0;
	        double hap = 0;

	         for (int i = 0; i < id.length-1; i++) {		//주민번호 검증
	            if(i == 8)
	            	j = 2.0f;
	        	hap += id[i] * j;
	        	j++;
	         }

	         double temp = 11.0f * (int)(hap / 11.0f) + 11.0f - hap;
	         double total = temp - 10.0f * ((int) (temp / 10.0f));
	         
	         if ((int)total == id[12]) {
	        	int year;
	 	        int month;
	 	        int day;
	 	        
	 	       switch(id[6]) {
				case 1 :
				case 2 : 
					year = 1900; break;
				case 3 :
				case 4 : 
					year = 2000; break;
				default : 
					year = 1800; 
				}//연도 설정
	 	        year = year + id[0] * 10 + id[1];
	 	        
	 	        if(id[2] == 0)
	 	        	month = id[3];
	 	        else
	 	        	month = id[2] * 10 + id[3];

	 	        if(id[4] == 0)
	 	        	day = id[5];
	 	        else
	 	        	day = id[4] * 10 + id[5];
	 	        
	 	        Calendar now = Calendar.getInstance();
	 	        int thisyear = now.get(Calendar.YEAR);
	 	        int age = thisyear - year;
	 			
	 	        MemberVO member = map.get(li.getSelectedItem());
	 			memberinfo = "\n\t\t이름 : " + member.getName() + 
	 					"\n\n\t\t생년월일 : " + year + "년 " + month + "월" + day + "일"
	 					+ "\n\n\t\t나이 : " + age
	 					+ "\n\n\t\t성별 : " + member.getGender()
	 					+ "\n\n\t\t취미 : " + member.getHobby();
	         }else 
	        	 memberinfo = "\n\t\t잘못된 주민번호입니다.\n\t\t주민번호를 수정해주세요.";
	       
			ta.setText(memberinfo);
			return;
		}
		
		
		//입력모드 눌렀을 때
		if(e.getSource()==initbt) {
			cls();
			setEditable(true);
			setButton(true);
			return;
		}
		
		//삭제버튼 눌렀을 때
		if(e.getSource()==delbt) {
			String imsi = li.getSelectedItem();
			map.remove(imsi);
			li.remove(imsi);
			cls();
			setButton(true);
			setEditable(true);
			ta.setText("\n\t\t성공적으로 삭제되었습니다.");
			return;
		}
		
		if(e.getSource()==editbt) {
			MemberVO member = map.get(li.getSelectedItem());
			String num1 = cho.getSelectedItem();
			String num2 = tf4.getText().trim();
			if(num2.trim().length()==0) return;
			String num3 = tf5.getText().trim();
			if(num3.trim().length()==0) return;
			String hb = "";
			for(int i=0; i<hobby.length; i++) {
				if(cb[i].getState()) {
					hb += cb[i].getLabel() + "/";
				}
			}
			if(hb.length()!=0) {
				hb = hb.substring(0, hb.length()-1);		//맨마지막의  / 제거
			}else {
				hb = "없음";
			}
			member.setNum1(num1); member.setNum2(num2); member.setNum3(num3);
			member.setHobby(hb);
			setButton(true);
	        setEditable(true);
	        cls();
	        ta.setText("\n\t\t성공적으로 수정되었습니다.");
	        tf1.requestFocus();
			return;
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==cho) {
			tf4.requestFocus();
			return;
		}
		
		//리스트 선택
		if(e.getSource()==li) {
			
			String temp = li.getSelectedItem();
			MemberVO member = map.get(temp);
			StringTokenizer tokens = new StringTokenizer(member.getHobby(), "/");

			tf1.setText(member.getName());
			tf2.setText(member.getId().substring(0,6));
			tf3.setText(member.getId().substring(6));
			cho.select(member.getNum1());
			tf4.setText(member.getNum2());
			tf5.setText(member.getNum3());
			
			if(member.getGender().equals("여성"));
				woman.setState(true);
			
			while(tokens.hasMoreTokens()) {
				String imsi = tokens.nextToken();
				for(int i = 0; i < cb.length; i++) {
					if(cb[i].getLabel().equals(imsi))
						cb[i].setState(true);
				}
			}
			setButton(false);
			setEditable(false);
			
		}//end list
	}

	@Override
	public void focusGained(FocusEvent e) {}

	@Override
	public void focusLost(FocusEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource()==tf2) {
			if(tf2.getText().trim().length()==6)
				tf3.requestFocus();
		}
		
		if(e.getSource()==tf3) {
			if(tf3.getText().trim().length()==7)
				cho.requestFocus();
		}
		
		if(e.getSource()==tf4) {
			if(tf4.getText().trim().length()==4)
				tf5.requestFocus();
		}
	
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	
	public static void main(String[] args) {
		new CMSManager();

	}




}
