package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageResize extends JFrame {
	private JLabel lblImg;
	
	public ImageResize() {
		Image img = Toolkit.getDefaultToolkit().getImage("ch.jpg");
		Image newSize = img.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
		
		lblImg = new JLabel(new ImageIcon(newSize));
		
		add(lblImg, BorderLayout.CENTER);
		
		setTitle("zz");
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new ImageResize();
	}
}
