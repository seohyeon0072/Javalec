package kr.ac.green.second;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class MyActionListener implements ActionListener {

	private Counter ui;

	public MyActionListener(Counter ui) {
		this.ui = ui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JLabel lblNum = ui.getLblNum();
		String strNum = lblNum.getText();
		// 2. 스트링 -> 숫자
		int num = Integer.parseInt(strNum);
		// 3. + 1
		num++;
		// 4. int -> String
		strNum = String.valueOf(num);

		lblNum.setText(strNum);
	}

}
