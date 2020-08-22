package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Quiz3 extends JFrame {
	public Quiz3() {
		JPanel backGround = new JPanel();
		JPanel leftBack = new JPanel(new GridLayout(0 , 1));
		JPanel rightBack = new JPanel(new BorderLayout());

		JPanel r1 = new JPanel();
		JLabel lbl1 = new JLabel("ID", JLabel.LEFT);
		JTextField tf1 = new JTextField(10);
		JButton btn1 = new JButton("중복체크");
		
		r1.add(lbl1);
		r1.add(tf1);
		r1.add(btn1);
		
		JPanel r2 = new JPanel();
		JLabel lbl2 = new JLabel("PW", JLabel.LEFT);
		JPasswordField tf2 = new JPasswordField(10);
		r2.add(lbl2);
		r2.add(tf2);

		JPanel r3= new JPanel();
		JLabel lbl3 = new JLabel("Re");
		JTextField tf3 = new JTextField(10);
		r3.add(lbl3);
		r3.add(tf3);
		
		leftBack.add(r1);
		leftBack.add(r2);
		leftBack.add(r3);
		
		JPanel r4 = new JPanel(new GridLayout(0, 1));
		JLabel gender = new JLabel("성별", JLabel.LEFT);
		r4.add(gender);
		
		JPanel radioPanel = new JPanel();
		ButtonGroup group = new ButtonGroup();
		JRadioButton a1 = new JRadioButton("남자");
		JRadioButton a2 = new JRadioButton("여자");
		
		group.add(a1);
		group.add(a2);
		
		radioPanel.add(a1);
		radioPanel.add(a2);
		r4.add(radioPanel);
		
		JLabel title = new JLabel("자기소개", JLabel.LEFT);
		
		JTextArea taInput = new JTextArea(6, 30);
		taInput.setLineWrap(true);
		taInput.setWrapStyleWord(true);
		
		
		
		rightBack.add(title, BorderLayout.NORTH);
		rightBack.add(taInput, BorderLayout.CENTER);
		

		leftBack.add(r1);
		leftBack.add(r2);
		leftBack.add(r3);
		leftBack.add(r4);
		
		backGround.add(leftBack);
		backGround.add(rightBack);

		add(backGround, BorderLayout.CENTER);

		setTitle("InformationForm");
		setSize(450, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Quiz3();

	}

}
