package kr.ac.green.four;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener3 implements ActionListener {
	private IPressable ui;

	public MyActionListener3(IPressable ui) {
		this.ui = ui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ui.buttonPressed();
	}
	
}
