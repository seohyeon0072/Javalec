package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MouseEventTest extends JFrame {
	
	private JLabel lblInfo; 
	
	public MouseEventTest() {
		init();
		setDisplay();
		addEvent();
		showFrame();
	}

	private void init() {
		lblInfo = new JLabel();
		
	}

	private void setDisplay() {
		
		add(lblInfo, BorderLayout.NORTH);
		
		
	}

	private void addEvent() {
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				String info = "X : " + e.getX() + " Y : " + e.getY();
				lblInfo.setText(info);
			}
			
		});
	}

	private void showFrame() {
		setTitle("ÁÂÇ¥");
		setSize(300, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new MouseEventTest();

	}

}
