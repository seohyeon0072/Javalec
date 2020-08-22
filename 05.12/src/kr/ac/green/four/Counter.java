package kr.ac.green.four;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Counter extends JFrame implements IPressable {

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
		btnPlus.addActionListener(new MyActionListener3(this));
	}

	private void shomFrame() {
		setTitle("¤¾¤¾¤¾");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void buttonPressed() {
		String strNum = lblNum.getText();
		int num = Integer.parseInt(strNum);
		num++;
		strNum = String.valueOf(num);

		lblNum.setText(strNum);
	}

	public static void main(String[] args) {
		new Counter();
	}
}