package kr.ac.green.another;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
		int some = 100;
		
		btnPlus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// JDK 1.8 이후 부터 된다.
				// 이하 버전에서는 에러
				System.out.println(some);
				
				int num = Integer.parseInt(lblNum.getText());
				num++;
				lblNum.setText(String.valueOf(num));
			}
		});
		
		//이너 클래스 2번째 형태
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				int result = JOptionPane.showConfirmDialog(
						Counter.this , "종료 하실?", "종료", 
						JOptionPane.OK_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE
				);
				if(result == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});
	}

	private void shomFrame() {
		setTitle("ㅎㅎㅎ");
		setSize(400, 300);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Counter();
	}

}
