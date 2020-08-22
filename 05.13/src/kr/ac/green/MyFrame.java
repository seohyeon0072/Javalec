package kr.ac.green;

import javax.swing.JFrame;

public class MyFrame extends JFrame{
	
	public MyFrame() {
		
		
		setTitle("MyFrame");
		setSize(300,400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MyFrame();
	}
	
}