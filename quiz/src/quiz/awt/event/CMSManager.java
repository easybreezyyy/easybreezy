package quiz.awt.event;

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
import java.util.HashMap;
import java.util.Map;

public class CMSManager extends Frame implements FocusListener, ItemListener, ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	
	//---------------Memberinfo-------------------//
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
		
		private TextField tf1 = new TextField(20);			//�̸� �Է�â
		private TextField tf2 = new TextField(6);			//������� �Է�â
		private TextField tf3 = new TextField(7);			//�ֹ� �޹�ȣ �Է�â
		private TextField tf4 = new TextField(4);			//�ڵ��� �߰� ��ȣ �Է�â
		private TextField tf5 = new TextField(4);			//�ڵ��� �޹�ȣ �Է�â
		private TextArea ta = new TextArea(10,20);			//�м�â
		
		private java.awt.List li = new java.awt.List(12);	//java.util List�� �򰥸���� ������ �� �ѹ� �̷��� ������Ѵ�.
		private Choice cho = new Choice();
		private String[] num = {"010", "017", "011"};
		private Label bar = new Label("-",Label.CENTER);
		
		private CheckboxGroup cg = new CheckboxGroup();
			private Checkbox man = new Checkbox("����", cg, true);
			private Checkbox woman = new Checkbox("����", cg, false);
		
		private String[] hobby = {"����","��ȭ","����","����","����"};
		private Checkbox[] cb = new Checkbox[hobby.length];
		
		private Button addbt = new Button("���");
		private Button analbt = new Button("�м�");
		private Button editbt = new Button("����");
		private Button delbt = new Button("����");
		private Button initbt = new Button("�Է¸��");
		private Label lb1 = new Label("�������� �Է�", Label.CENTER);
		private Label lb2 = new Label("�� ü �� ��", Label.CENTER);
		private Label lb3 = new Label("�� �� �� �� �� ��", Label.CENTER);
		private Label glb1= new Label("��      �� :", Label.CENTER);
		private Label glb2 = new Label("�ֹι�ȣ :", Label.CENTER);
		private Label glb3 = new Label("��ȭ��ȣ :", Label.CENTER);
		private Label glb4 = new Label("��      �� :", Label.CENTER);
		private Label glb5 = new Label("��      �� :", Label.CENTER);

		//------------------------Dialog-------------------------------//
		private Dialog dialog = new Dialog(this,"���� ����",false);
		private Label dlabel = new Label("Customer Manager Version 1.0",Label.CENTER);
		private Button dbt = new Button("Ȯ��");
		
		
		//--------------������---------------//
		public CMSManager() {
			super("Customer Manager event ver.");
			setEvent();
			//setDialog();
			setMenu();
			buildGUI();
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		}
		
		private void insert(MemberVO member) {
			map.put(member.getName(), member);
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
		 * �޴� ����
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
		 * ���̾�α� ����
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
			
			/*
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					
				}
			});
			*/
		}

		/**
		 *ȭ�鱸��
		 */
		private void buildGUI() {

			setLayout(new BorderLayout(3,3));		// �г� �� ������ ��...��
			setBackground(Color.cyan);

			add("North",new Label());	add("South",new Label());	//������ ��^^...
			add("West",new Label());	add("East",new Label());
		
			Panel pinput = new Panel(new BorderLayout(3,3));		//���������Է� �г�
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
			
			Panel plist = new Panel(new BorderLayout(3,3));		//��ü��� �г�
				plist.add("North", lb2);	plist.add("Center",li);
			
			Panel panal = new Panel(new BorderLayout(3,3));		//���������м� �г�
				panal.add("North",lb3);		panal.add("Center",ta);
			
			Panel wrapper = new Panel(new BorderLayout());		//�г� ��ü�� ���� ���̾ƿ�
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
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==tf1)
			tf2.requestFocus();
		
		if(e.getSource()==addbt) {
			ta.setText("		���������� ��� �Ǿ����ϴ�.");
			li.add(tf1.getText());
			MemberVO member = new MemberVO(tf1.getText(),tf2.getText()+tf3.getText(),
					cho.getSelectedItem()+tf4.getText()+tf5.getText(),"����","");		//��� �𸣰ھ�!!!
			insert(member);
			
			tf1.setText(""); tf2.setText("");
			tf3.setText(""); tf4.setText("");
			tf5.setText("");
			
			cho.select(0);
			
			if(!man.getState()) {			//���ڶ�� ���ڷ� �����ϰ� �������� ��ư ��ġ ����
				member.setGender("����");
				man.setState(true);
			}
			
			for(int i=0; i<hobby.length; i++) {
				if(cb[i].getState()==true) {
					cb[i].setState(false);
				}
			}
			
			
		}
			
			

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==li) {
			
			String temp = li.getSelectedItem();
			MemberVO member = map.get(temp);
			
			tf1.setText(member.getName());
			tf2.setText(member.getId().substring(0,6));
			tf3.setText(member.getId().substring(6,13));
			if(member.getGender()=="����")
				woman.setState(true);
			
			
			
			tf1.setEditable(false);
			tf2.setEditable(false);
			tf3.setEditable(false);
			man.setEnabled(false); woman.setEnabled(false);
			
			addbt.setEnabled(false);
			analbt.setEnabled(true);	editbt.setEnabled(true);
			delbt.setEnabled(true);		initbt.setEnabled(true);
		}
			

	}

	@Override
	public void focusGained(FocusEvent e) {
		

	}
	
	

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
