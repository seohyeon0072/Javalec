package kr.ac.green.third;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Counter extends JFrame {

	private JLabel lblNum;
	private JButton btnPlus;

	public Counter() {
		init();
		setDisplay();
		addListeners();
		shomFrame();

	}

	private void init() {
		lblNum = new JLabel("0", JLabel.CENTER);
		lblNum.setFont(new Font(Font.DIALOG, Font.BOLD, 60));

		btnPlus = new JButton("Plus");
	}

	private void setDisplay() {
		add(lblNum, BorderLayout.CENTER);
		add(btnPlus, BorderLayout.SOUTH);
	}

	private void addListeners() {
		btnPlus.addActionListener(new MyActionListener2(this));
	}

	private void shomFrame() {
		setTitle("ㅎㅎㅎ");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void plusNum() {
		String strNum = lblNum.getText();
		// 2. 스트링 -> 숫자
		int num = Integer.parseInt(strNum);
		// 3. + 1
		num++;
		// 4. int -> String
		strNum = String.valueOf(num);

		lblNum.setText(strNum);
	}

	public static void main(String[] args) {
		new Counter();
	}

}
