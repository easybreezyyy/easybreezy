package awt.event.exam04;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ItemEvent01 extends Frame implements ItemListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private Label lb = new Label("bloodtype : ", Label.RIGHT);
	private Label lb1 = new Label ("birth : ", Label.RIGHT);
	private Label lb2 = new Label("State : ", Label.RIGHT);
	
	private TextField bloodtype = new TextField();
	private TextField birth = new TextField();
	private TextField area = new TextField();
	
	private Choice blood = new Choice();
	private Choice year = new Choice();
	private Choice month = new Choice();
	private Choice day = new Choice();
	
	private List li = new List(3, false);
	
	private Button end = new Button("END");
	
	
	public ItemEvent01() {
		super("Item event example");
		setForm();
		bloodtype.setEditable(false);
		birth.setEditable(false);
		area.setEditable(false);
		blood.addItemListener(this);	day.addItemListener(this);
		month.addItemListener(this);	year.addItemListener(this);
		li.addActionListener(this);
		setSize(300,200);	setVisible(true);
		blood.requestFocus();
	}
	

	private void setForm() {
		setLayout(new GridLayout(2,1));
		
		Panel p = new Panel(new BorderLayout());
			Panel p1 = new Panel(new GridLayout(3,1));
				p1.add(lb);	p1.add(lb1);	p1.add(lb2);
			p.add("West", p1);
			
			Panel p2 = new Panel(new GridLayout(3,1));
				p2.add(bloodtype); p2.add(birth);	p2.add(area);
			p.add("Center",p2);
			
		Panel p3 = new Panel(new GridLayout(4,1));
		p3.add(blood); p3.add(year); p3.add(month); p3.add(day); 
		blood.add("A"); blood.add("B"); blood.add("AB"); blood.add("O");
		
		for(int i = 1980; i<= 2010; i++)
			year.add(i + "년");
		for(int i = 1; i<=12; i++)
			month.add(i + "월");
		for(int i = 1; i<=31; i++)
			day.add(i + "일");
		p.add("East",p3);
		
		add(p);
		
		Panel p4 = new Panel(new BorderLayout());
			p4.add("Center", li);
			li.add("서울"); li.add("대전"); li.add("대구"); li.add("부산");
			li.add("광주"); li.add("울산"); li.add("인천"); li.add("강원");
			p4.add("East", end);
			
		add(p4);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==li) {
			int i = li.getSelectedIndex();
			li.remove(i);
		}
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==blood) {
			String imsi = blood.getSelectedItem();
			bloodtype.setText(imsi);
		}else if(e.getSource()==day) {
			String imsi = year.getSelectedItem();
			String imsi1 = month.getSelectedItem();
			String imsi2 = day.getSelectedItem();
			birth.setText(imsi + imsi1 + imsi2); 
		}else if (e.getSource()==li) {
			String imsi = li.getSelectedItem();
			area.setText(imsi + "지역");
		}

	}

	public static void main(String[] args) {
		new ItemEvent01();
		System.out.println(1);
	}

}
