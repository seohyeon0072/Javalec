package kr.ac.green.other;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener4 implements ActionListener {

	private IFunction ui;
	
	public MyActionListener4(IFunction ui) {
		this.ui = ui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ui.change();
	}
}
