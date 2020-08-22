package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class InternationalFrame extends JFrame {

	/*
	 * JMenu=value JMenuItem=value JMenuItem=value
	 */

	private JMenuBar mBar;
	private JMenu mMenu;
	private JMenuItem mItem1;
	private JMenuItem mItem2;

	private JLabel lbl;

	private JPopupMenu pMenu;
	private JMenuItem pItem1;
	private JMenuItem pItem2;

	private File settingFile;
	private File settingFile2;
	

	public InternationalFrame() {
		settingFile = new File("setting.properties");
		settingFile2 = new File("setting2.properties");
		if (!(settingFile.exists())) {
			try {
				settingFile.createNewFile();
				settingFile2.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		init();
		setDisplay();
		addEvente();
		showFrame();
	}

	private void init() {

		mBar = new JMenuBar();
		mMenu = new JMenu("파일");
		mItem1 = new JMenuItem("열기");
		mItem2 = new JMenuItem("종료");
		lbl = new JLabel("레이블", JLabel.CENTER);

		pMenu = new JPopupMenu();
		pItem1 = new JMenuItem("한글");
		pItem2 = new JMenuItem("영어");
	}

	private void setDisplay() {
		mBar.add(mMenu);
		mMenu.add(mItem1);
		mMenu.add(mItem2);

		setJMenuBar(mBar);

		pMenu.add(pItem1);
		pMenu.add(pItem2);

		add(lbl, BorderLayout.CENTER);
	}

	private void addEvente() {
		lbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				showPopup(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				showPopup(e);
			}

		});

		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
				changeLang(command);
			}
		};

		pItem1.addActionListener(actionListener);
		pItem2.addActionListener(actionListener);

	}

	private void showFrame() {
		setSize(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);

	}

	private void changeLang(String command) {
		Properties prop = null;
		if (command.equals("한글") || command.equals("Korean")) {
			try {
				prop = MyUtils.loadProp(settingFile.getAbsolutePath());
				setAll(prop);
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				prop = MyUtils.loadProp(settingFile2.getAbsolutePath());
				setAll(prop);
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void setAll(Properties prop) {
		String k = null;
		for (Object key : prop.keySet()) {
			k = (String) key;
			if (k.equals("mMenu")) {
				mMenu.setText(prop.getProperty(k));
			} else if (k.equals("mItem1")) {
				mItem1.setText(prop.getProperty(k));
			} else if (k.equals("s2")) {
				pItem2.setText(prop.getProperty(k));
			} else if (k.equals("s1")) {
				pItem1.setText(prop.getProperty(k));
			} else if (k.equals("lbl")) {
				lbl.setText(prop.getProperty(k));
			} else if (k.equals("mItem2")) {
				mItem2.setText(prop.getProperty(k));
			}
		}
	}

	private void showPopup(MouseEvent me) {
		if (me.isPopupTrigger()) {
			pMenu.show(lbl, me.getX(), me.getY());
		}
	}

	public static void main(String[] args) {
		new InternationalFrame();
	}

}
