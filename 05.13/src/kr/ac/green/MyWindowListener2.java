package kr.ac.green;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.ac.green.second.MyFrame;

public class MyWindowListener2 extends WindowAdapter {
	
	private Counter frame;
	
	public MyWindowListener2(Counter frame) {
		this.frame = frame;
	}
	

	@Override
	public void windowClosing(WindowEvent e) {
		frame.closeWindow();
	}

}
