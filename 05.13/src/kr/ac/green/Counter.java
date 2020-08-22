package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Counter extends JFrame implements ActionListener {

	private JLabel lblNum;
	private JButton btnPlus;
	private JButton btnMinus;
	private JButton btnDefault;

	public JLabel getLblNum() {
		return lblNum;
	}

	public void setLblNum(JLabel lblNum) {
		this.lblNum = lblNum;
	}

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
		btnMinus = new JButton("Minus");
		btnDefault = new JButton("btnDefault");
	}

	private void setDisplay() {

		JPanel pnlSouth = new JPanel(new GridLayout(1, 2));
		pnlSouth.add(btnPlus);
		pnlSouth.add(btnMinus);
		pnlSouth.add(btnDefault);

		add(lblNum, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}

	private void addListeners() {
		btnPlus.addActionListener(this);
		btnMinus.addActionListener(this);
		btnDefault.addActionListener(this);
		
		WindowListener listener = new MyWindowListener2(this);
		addWindowListener(listener);
	}

	private void shomFrame() {
		setTitle("카운터");
		setSize(400, 300);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		/*
		 * 동일한 이벤트에 대해 이벤트 소스를 기준으로 구분한다.
		 */

		// 이벤트 소스를 구함(return Object)
		// 어떤 이벤트 객체라도 다 된다..
		// Object src = ae.getSource();
		// if(src == btnPlus) {
		// System.out.println("plus");
		// } else {
		// System.out.println("minus");
		// }

		/*
		 * 버튼에 동일한 문자열로 있으면 setActionCommand(); 로 이름을 지을 수 있다. 
		 * Action 에만 존재한다.
		 */
		/*
		 * String cmd = ae.getActionCommand(); System.out.println(cmd);
		 */

		int num = 0;
		Object src = ae.getSource();
		if (src != btnDefault) {
			num = Integer.parseInt(getLblNum().getText());

			if (src == btnPlus) {
				num += 1;
				getLblNum().setText(String.valueOf(num));

			} else if (src == btnMinus) {
				num -= 1;
				getLblNum().setText(String.valueOf(num));
			}
		}

		getLblNum().setText(String.valueOf(num));

	}

	public static void main(String[] args) {
		new Counter();
	}

	public void closeWindow() {
		int result = JOptionPane.showConfirmDialog(this, "종료하실?", "종료", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

}