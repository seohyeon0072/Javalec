package test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class help extends JFrame{
	public help() {
		JButton btn = new JButton("send");
		JTextField tf = new JTextField();
		
		add(btn,BorderLayout.NORTH);
		add(tf,BorderLayout.SOUTH);
		
		 ActionListener aListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btn){
					 if(tf.getText().equals("")){
						btn.setEnabled(false);
					 }else{
							btn.setEnabled(true);	 
					 }
				}
			}};

			tf.addActionListener(aListener);
		
			btn.addActionListener(aListener);
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);
		setResizable(false);
		setVisible(true);	}
	public static void main(String[] args) {
		new help();
	}
}
