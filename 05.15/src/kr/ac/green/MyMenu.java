package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MyMenu extends JFrame {
	private JMenuBar menu;
	private JMenu mFile;

	private JMenu mFeMale;
	private JMenuItem mFeMaleItem1;
	private JMenuItem mFeMaleItem2;
	private JMenuItem mFeMaleItem3;

	private JMenu mMale;
	private JMenuItem mMaleItem1;
	private JMenuItem mMaleItem2;
	private JMenuItem mMaleItem3;

	private JMenuItem mExit;

	private JLabel lblImg;

	public MyMenu() {
		init();
		setDisplay();
		addEvent();
		showFrame();
	}

	private void init() {
		menu = new JMenuBar();

		mFile = new JMenu("File");

		mFeMale = new JMenu("여자가수");
		mFeMaleItem1 = new JMenuItem("아이유");
		mFeMaleItem2 = new JMenuItem("에일리");
		mFeMaleItem3 = new JMenuItem("청하");

		mMale = new JMenu("남자가수");
		mMaleItem1 = new JMenuItem("후보 1번");
		mMaleItem2 = new JMenuItem("후보 2번");
		mMaleItem3 = new JMenuItem("후보 3번");

		mExit = new JMenuItem("Exit");

		lblImg = new JLabel();
	}

	private void setDisplay() {

		mFeMale.add(mFeMaleItem1);
		mFeMale.add(mFeMaleItem2);
		mFeMale.add(mFeMaleItem3);

		mMale.add(mMaleItem1);
		mMale.add(mMaleItem2);
		mMale.add(mMaleItem3);

		mFile.add(mFeMale);
		mFile.add(mMale);
		mFile.add(mExit);

		menu.add(mFile);

		setJMenuBar(menu);

		JPanel pnlCenter = new JPanel();
		pnlCenter.add(lblImg);

		add(pnlCenter, BorderLayout.CENTER);
	}

	private void addEvent() {
		ActionListener actionListener = (ae) -> {
			String command = ae.getActionCommand();

			if (command.equals("Exit")) {
				System.exit(0);
			}

			showImg(command);

			System.out.println(command);

		};

		mFeMaleItem1.addActionListener(actionListener);
		mFeMaleItem2.addActionListener(actionListener);
		mFeMaleItem3.addActionListener(actionListener);

		mMaleItem1.addActionListener(actionListener);
		mMaleItem2.addActionListener(actionListener);
		mMaleItem3.addActionListener(actionListener);

		mExit.addActionListener(actionListener);

	}

	private void showFrame() {
		setTitle("메뉴");
		setSize(300, 400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void showImg(String command) {
		if (command.equals("아이유")) {
			lblImg.setIcon(new ImageIcon("IU.jpg"));
		} else if (command.equals("에일리")) {
			lblImg.setIcon(new ImageIcon("al.jpg"));
		} else if (command.equals("청하")) {
			lblImg.setIcon(new ImageIcon("ch.jpg"));
		}
	}

	public static void main(String[] args) {
		new MyMenu();
	}
}
