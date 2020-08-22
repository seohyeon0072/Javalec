package kr.ac.green.other;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Quiz2 extends JFrame implements IFunction {
	public final static double MILE = 1.6;
	private JTextField tfValue;
	private JTextField tfSouth;
	private JButton btnCenter;

	public void init() {
		JPanel pnlTop = new JPanel();
		JLabel lblTitle = new JLabel("거리를 마일 단위로 입력하시오.");
		tfValue = new JTextField(5);

		pnlTop.add(lblTitle);
		pnlTop.add(tfValue);

		JPanel pnlCenter = new JPanel();
		btnCenter = new JButton("변환");
		pnlCenter.add(btnCenter);

		JPanel pnlSouth = new JPanel();
		tfSouth = new JTextField(20);
		tfSouth.setEditable(false);
		tfSouth.setBackground(Color.WHITE);
		pnlSouth.add(tfSouth);

		add(pnlTop, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

		setTitle("마일을 킬로미터로 변환");
		pack();
		setSize(350, 150);
		setLocation(100, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setVisible(true);

	}

	public Quiz2() {
		init();
		addListeners();

	}

	private void addListeners() {
		MyActionListener4 m = new MyActionListener4(this);
		btnCenter.addActionListener(m);
	}

	public static void main(String[] args) {
		new Quiz2();
	}

	@Override
	public void change() {
		String msg = "";
		try {
			double mile = Double.parseDouble(tfValue.getText());
			double km = mile * 1.6;
			msg = km + "km";

		} catch (NumberFormatException e) {
			msg = "숫자만 입력";

			/*
			 * 사용가능한 아이콘 플래스 
			 * JOptionPane.ERROR_MESSAGE
			 * JOptionPane.WARNING_MESSAGE 
			 * JOptionPane.INFORMATION_MESSAGE
			 * JOptionPane.PLAIN_MESSAGE 
			 * JOptionPane.QUESTION_MESSAGE
			 */
			JOptionPane.showMessageDialog(
					null, // 부모창
					msg // 메시지
//					"경고", // 타이틀
//					JOptionPane.QUESTION_MESSAGE // 아이콘
			);
		} finally {
			// 커버 변경
			tfValue.requestFocus();
			// 드래그
			tfValue.selectAll();
		}
		tfSouth.setText(msg);
	}
}