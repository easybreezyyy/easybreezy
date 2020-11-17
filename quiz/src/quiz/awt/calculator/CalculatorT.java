package quiz.awt.calculator;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CalculatorT extends Frame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private String[] suStr = { "7", "8", "9", "4", "5", "6", "1", "2", "3", "0", "+/-", "." };
	private Button[] subt = new Button[suStr.length];

	private String[] funStr = { "BackSpace", "CE", "C" };
	private Button[] funbt = new Button[funStr.length];

	private String[] operStr = { "+", "-", "*", "/" };
	private Button[] operbt = new Button[operStr.length];

	private Button equbt = new Button("=");

	private Label disp = new Label("0.", Label.RIGHT);

	private boolean first = true; // true면 처음으로 누른다. false 처음이 아니다.
	private boolean jumcheck = false; // false 점을 누른적 없다. true 점을 눌렀다.
	private char operator = '+';
	private double result = 0;

	public CalculatorT() {
		super("계산기");
		buildGUI();
		setEvent();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < subt.length - 2; i++) {
			if (e.getSource() == subt[i]) { // 0 ~ 9 숫자
				String imsi = subt[i].getLabel();
				if (first) {// 처음이냐?
					if (imsi.equals("0"))
						return;// 처음으로 누른게 0이라면
					if (jumcheck) {
						first = false;
						disp.setText("0.");
						return;
					} else {
						disp.setText(imsi + ".");
						first = false;
					}
				} else {// 처음이 아니냐
					String temp = disp.getText();
					if (jumcheck) {
						disp.setText(temp + imsi);
					} else {
						temp = temp.substring(0, temp.length() - 1);
						disp.setText(temp + imsi + ".");
					}
				}
			}
		} // end for
		if (e.getSource() == subt[subt.length - 2]) {// +/-
			if (first)
				return;
			String imsi = disp.getText();
			char sign = imsi.charAt(0);
			if (sign == '-')
				disp.setText(imsi.substring(1));
			else
				disp.setText("-" + imsi);
			return;
		}
		if (e.getSource() == subt[subt.length - 1]) {// .
			if (first) {
				disp.setText("0.");
			}
			jumcheck = true;
			first = false;
			return;
		}
		for (int i = 0; i < operbt.length; i++) {
			if (e.getSource() == operbt[i]) {
				operation();
				first = true;
				jumcheck = false;
				operator = operbt[i].getLabel().charAt(0);
			}
		} // end for
		if (e.getSource() == equbt) {
			operation();
			first = true;
			jumcheck = false;
			operator = '+';
			result = 0;
			return;
		}
		if (e.getSource() == funbt[0]) {// BackSpace
			if (first)
				return;

			String imsi = disp.getText();
			if (imsi.length() == 2) {// 2.
				disp.setText("0.");
				first = true;
				return;
			}
			if (imsi.charAt(0) == '-' && imsi.length() == 3) { // -3.
				disp.setText("0.");
				first = true;
				return;
			}

			if (jumcheck) {
				char test = imsi.charAt(imsi.length() - 1);
				if (test == '.') {
					disp.setText(imsi.substring(0, imsi.length() - 2) + ".");
					jumcheck = false;
				} else {
					disp.setText(imsi.substring(0, imsi.length() - 1));
				}
			} else {
				disp.setText(imsi.substring(0, imsi.length() - 2) + ".");
			}
		}
		if (e.getSource() == funbt[1]) {// CE
			first = true;
			jumcheck = false;
			disp.setText("0.");
			return;
		}
		if (e.getSource() == funbt[2]) {// C
			first = true;
			jumcheck = false;
			operator = '+';
			result = 0;
			disp.setText("0.");
			return;
		}

	}

	public void operation() {
		double number = Double.parseDouble(disp.getText());
		switch (operator) {
		case '+':
			result += number;
			break;
		case '-':
			result -= number;
			break;
		case '*':
			result *= number;
			break;
		case '/':
			result /= number;
			break;
		}
		double imsiNum = result - (int) result;
		if (imsiNum > 0) {
			disp.setText(String.valueOf(result));
		} else {
			disp.setText(String.valueOf((int) result) + ".");
		}
	}

	/**
	 * 이벤트 연결
	 */
	private void setEvent() {
		for (int i = 0; i < subt.length; i++)
			subt[i].addActionListener(this);
		for (int i = 0; i < funbt.length; i++)
			funbt[i].addActionListener(this);
		for (int i = 0; i < operbt.length; i++)
			operbt[i].addActionListener(this);
		equbt.addActionListener(this);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * 화면구성
	 */
	private void buildGUI() {
		setBackground(Color.CYAN);
		add("North", new Label());
		add("South", new Label());
		add("West", new Label());
		add("East", new Label());

		disp.setFont(new Font("Serif", Font.BOLD, 15));
		disp.setBackground(Color.white);

		Panel mainPanel = new Panel(new BorderLayout(3, 3));
		mainPanel.add("North", disp);

		Panel centerPanel = new Panel(new GridLayout(1, 2, 3, 3));
		Panel leftPanel = new Panel(new GridLayout(4, 3, 3, 3));
		for (int i = 0; i < subt.length; i++) {
			subt[i] = new Button(suStr[i]);
			subt[i].setFont(new Font("Serif", Font.BOLD, 15));
			leftPanel.add(subt[i]);
		}
		centerPanel.add(leftPanel);

		Panel rightPanel = new Panel(new GridLayout(4, 1, 3, 3));
		Panel p1 = new Panel(new GridLayout(1, 3, 3, 3));
		for (int i = 0; i < funbt.length; i++) {
			funbt[i] = new Button(funStr[i]);
			funbt[i].setFont(new Font("Serif", Font.BOLD, 15));
			p1.add(funbt[i]);
		}
		rightPanel.add(p1);

		Panel p2 = new Panel(new GridLayout(1, 2, 3, 3));
		Panel p3 = new Panel(new GridLayout(1, 2, 3, 3));
		for (int i = 0; i < operbt.length; i++) {
			operbt[i] = new Button(operStr[i]);
			operbt[i].setFont(new Font("Serif", Font.BOLD, 15));
			if (i < 2)
				p2.add(operbt[i]);
			else
				p3.add(operbt[i]);
		}
		rightPanel.add(p2);
		rightPanel.add(p3);

		equbt.setFont(new Font("Serif", Font.BOLD, 15));
		rightPanel.add(equbt);
		centerPanel.add(rightPanel);
		mainPanel.add("Center", centerPanel);
		add("Center", mainPanel);

		pack();
		// 화면 가운데로 보내기.
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension my = getSize();
		setLocation(scr.width / 2 - my.width / 2, scr.height / 2 - my.height / 2);

		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new CalculatorT();
	}

}
