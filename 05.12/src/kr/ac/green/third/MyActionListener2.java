package kr.ac.green.third;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener2 implements ActionListener {

	private Counter ui;

	public MyActionListener2(Counter ui) {
		this.ui = ui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ui.plusNum();
	}
}