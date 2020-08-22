package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.AbstractBorder;
import javax.swing.border.TitledBorder;

public class Quiz1 extends JFrame {
	private Font font = new Font(Font.DIALOG, Font.BOLD, 15);

	private JLabel lblTop;

	public Quiz1() {
		init();
		setDisplay();
		showFrame();
	}

	private void init() {
		lblTop = new JLabel("자바피자에 오신것을 환영합니다", JLabel.CENTER);
		
		String[] str1 = { "콤보", "포테이토", "불고기" };
		String[] str2 = { "피망", "치즈", "페페로니", "베이컨" };
		String[] str3 = { "small", "medium", "large" };
		
		JPanel pnlLeft = new JPanel(new GridLayout(0, 1));
		JPanel pnlCenter = new JPanel(new GridLayout(0, 1));
		JPanel pnlRight = new JPanel(new GridLayout(0, 1));

		AbstractBorder t1 = new TitledBorder("종류");
		pnlLeft.setBorder(t1);
		
		JCheckBox[] leftBox = new JCheckBox[str1.length];
		
		ButtonGroup group = new ButtonGroup();
		
		for(int i = 0; i < leftBox.length; i++) {
			leftBox[i] = new JCheckBox(str1[i]);
			pnlLeft.add(leftBox[i]);
			group.add(leftBox[i]);
		}
		
		t1 = new TitledBorder("토핑");
		pnlCenter.setBorder(t1);
		JRadioButton[] centerBox = new JRadioButton[str2.length];
		for(int i = 0; i < centerBox.length; i++) {
			centerBox[i] = new JRadioButton(str2[i]);
			pnlCenter.add(centerBox[i]);
		}
		
		t1 = new TitledBorder("크기");
		pnlRight.setBorder(t1);
		JCheckBox[] rightBox = new JCheckBox[str3.length];
		for(int i = 0; i < rightBox.length; i++) {
			rightBox[i] = new JCheckBox(str3[i]);
			pnlRight.add(rightBox[i]);
		}
		
		
		// btn
		JPanel btnPanel = new JPanel();

		JButton btn1 = new JButton("주문");
		JButton btn2 = new JButton("취소");

		btnPanel.add(btn1);
		btnPanel.add(btn2);

		JPanel backGround = new JPanel();
		pnlLeft.setPreferredSize(new Dimension(90, 100));
		pnlCenter.setPreferredSize(new Dimension(90, 100));
		pnlRight.setPreferredSize(new Dimension(90, 100));

		backGround.add(pnlLeft);
		backGround.add(pnlCenter);
		backGround.add(pnlRight);

		add(lblTop, BorderLayout.NORTH);
		add(backGround, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.SOUTH);

	}

	private void setDisplay() {
		setTitle("피자주문");
		setSize(350, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	private void showFrame() {

	}
	
	private JLabel getLabel(String text, AbstractBorder border) {
		JLabel lbl = new JLabel(text);
		lbl.setFont(font);
		lbl.setBorder(border);
		return lbl;
	}

	public static void main(String[] args) {
		new Quiz1();
	}

}
