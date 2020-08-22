package kr.ac.green.second;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MyFrame2 extends JFrame {
	public MyFrame2() {
		
		addWindowListener(new MyWindowListener2());
		
		setTitle("MyFrame2");
		setSize(300, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private class MyWindowListener2 extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent we) {
			
		}
	}
	
	public static void main(String[] args) {
		new MyFrame2();
	}

}
