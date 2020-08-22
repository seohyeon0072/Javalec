package kr.ac.green.second;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindowListener extends WindowAdapter {
	public static final int NORMAL = 0;

	private MyFrame myframe;
	public MyWindowListener(MyFrame myframe) {
		this.myframe = myframe;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		myframe.closeWindow();
		
		// 프로그램 종료(양수 정상 종료, 음수 비 정상 종료)
//				System.exit(NORMAL);
	}

}