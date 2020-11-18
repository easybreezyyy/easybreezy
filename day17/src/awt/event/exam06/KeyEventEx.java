package awt.event.exam06;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEventEx extends Frame implements KeyListener {

	private static final long serialVersionUID = 1L;
	private Label lb1 = new Label("ID : ", Label.RIGHT);
	private Label lb2 = new Label("-", Label.CENTER);
	TextField jumin1 = new TextField(10);
	TextField jumin2 = new TextField(10);
	Button bt = new Button("OK");
	
	KeyEventEx(){
		super("KeyEvent example");
		setForm();
		setEvent();
		pack();
		setVisible(true);
	}
	
	
	private void setEvent() {
		jumin1.addKeyListener(this);
		jumin2.addKeyListener(this);
		
	}


	private void setForm() {
		setLayout(new BorderLayout());
		add("West",lb1);
		Panel p = new Panel(new FlowLayout(FlowLayout.LEFT));
		p.add("West",jumin1);
		p.add("Center",lb2);
		p.add("East",jumin2);
		add("Center",p);
		
		Panel p1 = new Panel(new FlowLayout(FlowLayout.RIGHT));
		p1.add(bt); 
		add("South",p1);
	
	}


	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource()==jumin1) {
			if(jumin1.getText().trim().length()==6)
				jumin2.requestFocus();
		}
		if(e.getSource()==jumin2) {
			if(jumin1.getText().trim().length()==7)
				bt.requestFocus();
		}
		

	}

	public static void main(String[] args) {
		new KeyEventEx();

	}

}
