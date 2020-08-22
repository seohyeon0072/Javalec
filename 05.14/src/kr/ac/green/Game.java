package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class Game extends JFrame {
	public static final int MUK = 0;
	public static final int ZZI = 1;
	public static final int BBA = 2;

	private String[] str = { "묵", "찌", "빠" };

	private JButton btnStart;
	private JTextArea gameState;

	private JRadioButton muk;
	private JRadioButton zzi;
	private JRadioButton bba;

	private String userChoice;
	private String comChoice;

	public Game() {
		init();
		setDisplay();
		addEvent();
		showFrame();
	}

	private void init() {

		btnStart = new JButton("시작");

		muk = new JRadioButton("묵");
		zzi = new JRadioButton("찌");
		bba = new JRadioButton("빠");

		ButtonGroup radioGroup = new ButtonGroup();

		radioGroup.add(muk);
		radioGroup.add(zzi);
		radioGroup.add(bba);

		gameState = new JTextArea(4, 20);

		gameState.setBorder(new LineBorder(Color.black, 1));
		gameState.setEnabled(false);
	}

	private void setDisplay() {
		JPanel pnlNorth = new JPanel();

		pnlNorth.add(muk);
		pnlNorth.add(zzi);
		pnlNorth.add(bba);

		JPanel pnlCenter = new JPanel();

		pnlCenter.add(btnStart);

		JPanel pnlSouth = new JPanel(new BorderLayout());
		pnlSouth.add(gameState);

		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}

	private void addEvent() {

		// 라디오버튼 선택
		ActionListener actionListener = (e) -> {
			randomChoice();

			JRadioButton temp = (JRadioButton) e.getSource();
			userChoice = temp.getText();
		};

		muk.addActionListener(actionListener);
		zzi.addActionListener(actionListener);
		bba.addActionListener(actionListener);

		// 게임 진행
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (gameState != null) {
					gameState.setText("");
				}

				if (userChoice != null) {
					String result = gameStart();

					// 게임 진행하고 텍스트Area 에 출력
					gameState.append("컴퓨터 : " + comChoice + "\n");
					gameState.append("사용자 : " + userChoice + "\n");
					gameState.append("결 과 : " + result);
				} else {
					JOptionPane.showMessageDialog(Game.this, "버튼을 선택하셈");
				}
			}
		});
	}

	private void showFrame() {
		setTitle("묵찌빠");
		setSize(200, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	private void randomChoice() {
		int num = (int) (Math.random() * 3);
		comChoice = str[num];
	}

	private String gameStart() {
		
		String result = "";
		
		if (userChoice.equals(str[BBA])) {

			if (comChoice.equals(str[MUK])) {
				result = "이김";
			} else if (comChoice.equals(str[ZZI])) {
				result = "졌음";
			} else {
				result = "비김";
			}

		} else if (userChoice.equals(str[MUK])) {

			if (comChoice.equals(str[ZZI])) {
				result = "이김";
			} else if (comChoice.equals(str[BBA])) {
				result = "졌음";
			} else {
				result = "비김";
			}

		} else {
			if (comChoice.equals(str[BBA])) {
				result = "이김";
			} else if (comChoice.equals(str[MUK])) {
				result = "졌음";
			} else {
				result = "비김";
			}
		}

		return result;
	}

	public static void main(String[] args) {
		new Game();
	}
}