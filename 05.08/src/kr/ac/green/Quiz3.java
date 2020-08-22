package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Quiz3 extends JFrame {

	public Quiz3() {
		JPanel pnlTopBack = new JPanel();
		GridLayout gridLayout = new GridLayout(0,1);
		
		pnlTopBack.setLayout(new GridLayout(0,1));
		
		JPanel pnlTop = new JPanel();
		JLabel lblinfo = new JLabel("원금을 입력하시오 ");
		JTextField tfValue = new JTextField(5);
		pnlTop.add(lblinfo);
		pnlTop.add(tfValue);
		
		JPanel pnlTop2 = new JPanel();
		JLabel lblinfo2 = new JLabel("이자율을 입력하시오 ");
		JTextField tfValue2 = new JTextField(5);
		pnlTop2.add(lblinfo2);
		pnlTop2.add(tfValue2);

		JPanel pnlCenter = new JPanel();
		JButton btnCenter = new JButton("변환");
		pnlCenter.add(btnCenter);

		JPanel pnlSouth = new JPanel();
		JTextField tfSouth = new JTextField(15);
		tfSouth.setEditable(false);
		tfSouth.setBackground(Color.WHITE);
		pnlSouth.add(tfSouth);
		
		pnlTopBack.add(pnlTop);
		pnlTopBack.add(pnlTop2);
		
		//-----------------------//
		
		add(pnlTopBack, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

		setTitle("이자 계산기");
		pack();
		setSize(400, 300);
		setLocation(100, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}
	public static void main(String[] args) {
		new Quiz3();
	}
}
