package awt.event.exam03;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEvent02 extends Frame implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	private Button bt = new Button("ธÞที");
	
	public MouseEvent02() {
		setLayout(new FlowLayout());
		add(bt);
		bt.addMouseListener(this);
		setSize(600,500);
		setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//if(e.getSource()==this)
		//	bt.setLocation(e.getX(),e.getY());
			
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==bt) {
			int x = (int) (Math.random()*540)+10;
			int y = (int) (Math.random()*440)+31;
			bt.setLocation(x, y);
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public static void main(String[] args) {
		new MouseEvent02();
	}

}
