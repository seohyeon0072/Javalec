package kr.ac.green;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MyFrame3 extends JFrame{
	
	public MyFrame3() {
		
		//*******중요하다*******//
		//Anonymous Inner Class
		/*
		 * 1. 객체 생성을 한번 만 할 수 있다( 이름이 없다.. )
		 */
		addWindowListener(new WindowAdapter() {
			//중괄호 까지가 클래스
			//WindowAdapter 를 상속하거나 구현한 것임
			@Override
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		
		
		setTitle("MyFrame3");
		setSize(300,400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MyFrame3();
	}

}
