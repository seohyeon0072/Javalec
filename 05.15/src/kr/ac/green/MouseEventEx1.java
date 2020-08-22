package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

public class MouseEventEx1 extends JFrame {
	private JLabel lblLeft;
	private JLabel lblRight;
	private JLabel lblStatus;

	private JSplitPane sp;

	public MouseEventEx1() {
		init();
		setDisplay();
		addListener();
		showFrame();
	}

	private void init() {
		lblLeft = new JLabel();
		lblLeft.setOpaque(true);
		lblLeft.setBackground(Color.YELLOW);
		lblLeft.setHorizontalAlignment(JLabel.CENTER);

		lblRight = new JLabel();
		lblRight.setOpaque(true);
		lblRight.setBackground(Color.PINK);
		
		lblStatus = new JLabel();

		sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		sp.setContinuousLayout(true);
		// LEFT(호리즌탈) , TOP(버티컬) 기준
		sp.setResizeWeight(0.3);

		sp.setDividerSize(5);

		sp.setOneTouchExpandable(true);
	}

	private void setDisplay() {
		sp.setLeftComponent(lblLeft);
		sp.setRightComponent(lblRight);

		add(sp, BorderLayout.CENTER);
		add(lblStatus, BorderLayout.SOUTH);

	}
	private void setStatus(int x , int y) {
		lblStatus.setText("status : " + x + ", " + y);
	}

	private void addListener() {
		MouseListener listener = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblLeft.setText("in");
				lblLeft.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblLeft.setText("");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				lblLeft.setBackground(Color.GREEN);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				lblLeft.setBackground(Color.YELLOW);
			}

		};

		lblLeft.addMouseListener(listener);

		lblRight.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				// 1 : 왼쪽 버튼 , 2 : 휠 , 3 : 오른쪽 버튼
				int btnNum = e.getButton();
				if (btnNum == java.awt.event.MouseEvent.BUTTON1) {
					lblRight.setText("버튼1");
					if (e.getClickCount() == 2) {
						setStatus(e.getX(), e.getY());
						System.out.println(e.getXOnScreen());
						System.out.println(e.getYOnScreen());

					}
				} else if (btnNum == java.awt.event.MouseEvent.BUTTON2) {
					lblRight.setText("버튼2");
				} else if (btnNum == java.awt.event.MouseEvent.BUTTON3) {
					lblRight.setText("버튼3");
				}
			}

		});
	}

	private void showFrame() {
		setTitle("zz");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MouseEventEx1();
	}
}
