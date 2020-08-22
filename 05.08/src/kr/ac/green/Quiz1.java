package kr.ac.green;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Quiz1 extends JFrame {
	private JLabel lblTitle;
	private JButton[] btn;

	public Quiz1() {

		lblTitle = new JLabel("자바피자에 오신것을 환영합니다. 피자를 고르세요.", JLabel.CENTER);

		JPanel pnlCenter = new JPanel();

		String[] str = { "콤보피자", "포테이토피자", "불고기피자" };
		btn = new JButton[str.length];
		for (int i = 0; i < str.length; i++) {
			btn[i] = new JButton(str[i]);
			pnlCenter.add(btn[i]);
		}

		add(lblTitle, BorderLayout.CENTER);
		add(pnlCenter, BorderLayout.SOUTH);

		setTitle("Quiz1");
		setSize(500, 200);
		setLocation(100, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new Quiz1();
	}

}
