package kr.ac.green.second;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Counter extends JFrame {

	private JLabel lblNum;
	private JButton btnPlus;
	private MyActionListener myAction;

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
		myAction = new MyActionListener(this);
		btnPlus.addActionListener(myAction);
	}

	private void shomFrame() {
		setTitle("¤¾¤¾¤¾");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public JLabel getLblNum() {
		return lblNum;
	}

	public void setLblNum(JLabel lblNum) {
		this.lblNum = lblNum;
	}

	public static void main(String[] args) {
		new Counter();
	}

}
