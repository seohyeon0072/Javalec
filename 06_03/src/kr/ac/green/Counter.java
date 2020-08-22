package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Counter extends JFrame implements ActionListener {
	private JLabel lblNum;
	private JButton btnPlus;
	
	public Counter() {
		lblNum = new JLabel("0", JLabel.CENTER);
		lblNum.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
		
		btnPlus = new JButton("Plus");
		
		add(lblNum, BorderLayout.CENTER);
		add(btnPlus, BorderLayout.SOUTH);
		
		btnPlus.addActionListener(this);
		
		setTitle("Counter");
		setSize(300, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
	public void setCounter(CounterInfo info) {
		setNumber(info.getNum());
		setTitle(info.getTitle());
		setLocation(info.getLocation());
		setSize(info.getSize());
	}
	
	
	public int getNumber() {
		return Integer.parseInt(lblNum.getText());
	}
	public void setNumber(int number) {
		lblNum.setText(String.valueOf(number));
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		lblNum.setText(
			String.valueOf(Integer.parseInt(lblNum.getText()) + 1)
		);
	}	
}









