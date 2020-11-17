package awt.event.exam03;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEvent01 extends Frame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private Button bt1 = new Button("OK");
	private Button bt2 = new Button("Cancel");
	
	public MouseEvent01() {
		super("Mouse event example");
		setLayout(new FlowLayout());
		add(bt1); add(bt2);
		setEvent();
		setSize(300,200);
		setVisible(true);
	}
	
	public void setEvent() {			//이벤트 연결
		bt1.addMouseListener(this);
		bt2.addMouseListener(this);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==bt1)
			System.out.println("you clicked OK button");
		if(e.getSource()==bt2)
			System.out.println("you clicked Cancel button");

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource()==bt1)
			System.out.println("you PRESSED the OK button");
		if(e.getSource()==bt2)
			System.out.println("you PRESSED the CANCEL button");

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource()==bt1)
			System.out.println("you just DETACHED the OK button");
		if(e.getSource()==bt2)
			System.out.println("you just DETACHED the CANCEL button");

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==bt1 || e.getSource()==bt2)
			System.out.println("you are on button now");

	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource()==bt1 || e.getSource()==bt2)
			System.out.println("you leave the button now");

	}

	public static void main(String[] args) {
		new MouseEvent01();
	}

}
