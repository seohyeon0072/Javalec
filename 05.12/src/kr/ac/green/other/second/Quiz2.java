package kr.ac.green.other.second;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Quiz2 extends JFrame{
	public Quiz2() {
		
		JPanel pnlTop = new JPanel();
		JLabel lblTitle = new JLabel("거리를 마일 단위로 입력하시오.");
		JTextField tfValue = new JTextField(5);
		
		pnlTop.add(lblTitle);
		pnlTop.add(tfValue);
		
		JPanel pnlCenter = new JPanel();
		JButton btnCenter = new JButton("변환");	
		pnlCenter.add(btnCenter);
		
		JPanel pnlSouth = new JPanel();
		JTextField tfSouth = new JTextField(20);
		tfSouth.setEditable(false);
		tfSouth.setBackground(Color.WHITE);
		pnlSouth.add(tfSouth);
		
		add(pnlTop, BorderLayout.NORTH);
		add(pnlCenter , BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
		
		setTitle("마일을 킬로미터로 변환");
		pack();
		setSize(350, 150);
		setLocation(100, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void buttonPressed() {
		
	}
	public static void main(String[] args) {
		new Quiz2();
	}

}
