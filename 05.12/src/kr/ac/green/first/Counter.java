package kr.ac.green.first;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * 버튼을 누르는 행위 : 액션 이벤트(Action Event) -> ActionListener
 * 
 */
public class Counter extends JFrame implements ActionListener {

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

	/*
	 * EventSource , EventListener 연결
	 */
	private void addListeners() {
		btnPlus.addActionListener(this);
	}

	private void shomFrame() {
		setTitle("ㅎㅎㅎ");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// ActionEvent 발생 시 할 일 정의
		// 1. 숫자를 들고 온다.
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
