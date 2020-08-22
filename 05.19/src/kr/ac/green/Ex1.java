package kr.ac.green;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Ex1 extends JPanel {
	
	public Ex1() {
		setSize(200,200);
		setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawRect(50, 50, 100, 100);
	}

	public static void main(String[] args) {
		new Ex1();
	}

}
