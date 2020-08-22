package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

/*
 * 메뉴는 배치의 대상이 아님 . 기본 위치가 약속되어 있음 
 * 
 * 메뉴 구성요소
 * 1. JMenuBar : JMenu를 포함할 수 있는 Component
 * 2. JMenu : 기능(자신이 포함하는 메뉴 아이템을 보여준다)
 * 3. JMenuItem , JRadioButtonMenuItem, JCheckBoxMenuItem : 기능이 존재
 */

public class MenuEx extends JFrame {

	private JMenuBar mBar;
	
	private JMenu mFile;
	private JMenuItem miOpen;
	private JMenuItem miSave;
	private JMenuItem miExit;

	private JMenu mOption;
	private JRadioButtonMenuItem rbmiOptionA;
	private JRadioButtonMenuItem rbmiOptionB;

	private JLabel lblMain;
	private JButton btnExit;

	public MenuEx() {
		init();
		setDisplay();
		addListener();
		showFrame();
	}

	private void init() {
		// //컴포넌트 생성하기 전에 해야함......... 꼭 제일 위쪽
		// try {
		// String lookNFeelName = UIManager.getSystemLookAndFeelClassName();
		// UIManager.setLookAndFeel(lookNFeelName);
		//
		// } catch (Exception e) {
		//
		// }

		lblMain = new JLabel("Start", JLabel.CENTER);
		lblMain.setFont(new Font(Font.DIALOG, Font.BOLD, 40));

		btnExit = new JButton("Exit");
		// alt
		btnExit.setMnemonic('X');
		btnExit.setToolTipText("alt + x");

		mBar = new JMenuBar();

		mFile = new JMenu("File");
		miOpen = new JMenuItem("Open", 'O');
		// miOpen.setIcon(new ImageIcon("open.png"));

		// ctrl + s
		// 키 조합을 나타내는 객체
		KeyStroke keys = KeyStroke.getKeyStroke(
				// keyChar , modifiers(설정키)
				KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK);

		miSave = new JMenuItem("Save");
		miSave.setMnemonic('S');
		miSave.setAccelerator(keys);

		// miSave.setIcon(new ImageIcon("save.png"));
		miExit = new JMenuItem("Exit");
		miExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_DOWN_MASK));

		mOption = new JMenu("Option");
		rbmiOptionA = new JRadioButtonMenuItem("Option A", true);
		rbmiOptionB = new JRadioButtonMenuItem("Option B");

		ButtonGroup group = new ButtonGroup();
		group.add(rbmiOptionA);
		group.add(rbmiOptionB);

	}

	private void setDisplay() {
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnExit);

		add(lblMain, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

		// 메뉴 조립
		mOption.add(rbmiOptionA);
		mOption.add(rbmiOptionB);

		mFile.add(miOpen);
		mFile.add(miSave);

		mFile.addSeparator();
		mFile.add(mOption);
		// 경계선
		mFile.addSeparator();
		mFile.add(miExit);

		mBar.add(mFile);

		JMenu mEtc = new JMenu("ETC");

		JMenuItem miInfo = mEtc.add("about swing");
		JMenuItem miHelp = mEtc.add("help");

		mBar.add(mEtc);

		// 메뉴는 배ㅣ 대상이 아님
		setJMenuBar(mBar);
	}

	private void addListener() {
		ActionListener listener = (e) -> {
			String command = e.getActionCommand();
			// lblMain.setText(e.getActionCommand());
			if (command.equalsIgnoreCase("Exit")) {
				closeWindow();
			} else {
				lblMain.setText(command);
			}

		};

		miOpen.addActionListener(listener);
		miSave.addActionListener(listener);
		miExit.addActionListener(listener);
		btnExit.addActionListener(listener);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				closeWindow();
			}
		});
	}

	private void closeWindow() {
		JOptionPane.showMessageDialog(this, "종료", "Exit", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	private void showFrame() {
		setTitle("메뉴");
		setSize(300, 400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MenuEx();
	}
}