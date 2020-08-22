package kr.ac.green.second;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * 1. setDefaultCloseOperation(; 반드시 처리
 * 2. 윈도우리스터를 사용하지 말고 윈도우 어댑터를 사용할 것
 */
public class MyFrame extends JFrame {

	public MyFrame() {
		WindowListener listener = new MyWindowListener(this);
		addWindowListener(listener);
		
		setTitle("MyFrame");
		setSize(300, 400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);

	}

	public void closeWindow() {
		// OK_CANCEL_OPTION -> 예, 아니오
		// YES_NO_OPTION -> 확인, 취소
		// YES_NO_CANCEL_OPTION -> 예, 아니오, 취소
		int result = JOptionPane.showConfirmDialog(this, "종료하실?", "종료", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new MyFrame();
	}

}