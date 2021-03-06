import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class SimplaPad extends JFrame {
	public static final int IS_OK = 0;
	public static final int IS_NO = 1;
	public static final int IS_CANCLE = 2;

	private JMenuBar mBar;
	private JMenu mFile;
	private JMenuItem mNewFile;
	private JMenuItem mOpen;
	private JMenuItem mSave;
	private JMenuItem mSaveAs;
	private JMenuItem mExit;

	private JTextArea taEditor;

	private JFileChooser chooser;

	private File sourceFile;

	private String title = "SimplePad ver 1.0";

	private boolean saveCheck;
	private boolean fileCheck;
	private boolean modifier;

	public SimplaPad() {
		init();
		setDisplay();
		addListeners();
		showFrame();
	}

	private void init() {
		mBar = new JMenuBar();
		mFile = new JMenu("File");
		mFile.setMnemonic(KeyEvent.VK_F);

		mNewFile = new JMenuItem("NewFile");
		mNewFile.setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));

		mOpen = new JMenuItem("Open");
		mOpen.setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK));

		mSave = new JMenuItem("Save");
		mSave.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));

		mSaveAs = new JMenuItem("Save As");
		mSaveAs.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK + Event.ALT_MASK));

		mExit = new JMenuItem("Exit");
		mExit.setAccelerator(KeyStroke.getKeyStroke('X', Event.ALT_MASK));

		mFile.add(mNewFile);
		mFile.add(mOpen);
		mFile.add(mSave);
		mFile.add(mSaveAs);
		mFile.add(mExit);

		mBar.add(mFile);

		taEditor = new JTextArea();
		taEditor.setTabSize(4);
		taEditor.setLineWrap(true);
		taEditor.setWrapStyleWord(true);
		
//		"." (현재 파일의 경로)
		chooser = new JFileChooser();
	}

	private void setDisplay() {
		setJMenuBar(mBar);
		add(new JScrollPane(taEditor), BorderLayout.CENTER);

	}

	private void addListeners() {
		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				Object o = ae.getSource();
				if (o == mOpen) {
					openEvent();
				} else if (o == mSave) {
					saveEvent();
				} else if (o == mExit) {
					exitEvent();
				} else if (o == mNewFile) {
					newFileEvent();
				} else if (o == mSaveAs) {
					saveAsEvent();
				}
			}

		};

		mOpen.addActionListener(actionListener);
		mSave.addActionListener(actionListener);
		mExit.addActionListener(actionListener);
		mNewFile.addActionListener(actionListener);
		mSaveAs.addActionListener(actionListener);

		Document doc = taEditor.getDocument();
		doc.addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (sourceFile != null && fileCheck) {
					setTitle(title + sourceFile.getName().toString() + " *");
				}

				if (fileCheck == false && taEditor.getText().length() != 0) {
					setTitle(title + "*");
					modifier = true;
				}

			}

			// 문서 안의 스타일 변경 감지 .... (글꼴 ..)
			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});
	}

	private void exitEvent() {
		if (sourceFile != null && saveCheck) {
			System.exit(0);
		} else if (sourceFile == null && saveCheck == false && modifier != false) {
			int result = JOptionPane.showConfirmDialog(this, "저장하고 종료?", "알림", JOptionPane.INFORMATION_MESSAGE);
			if (result == IS_OK) {
				saveEvent();
				System.exit(0);
			} else if (result == IS_NO) {
				System.exit(0);
			}
		} else {
			int result = JOptionPane.showConfirmDialog(this, "그냥 종료??", "알림", JOptionPane.YES_NO_OPTION);

			if (result == IS_OK) {
				System.exit(0);
			}
		}
	}

	private void saveAsEvent() {
		int result = chooser.showSaveDialog(this);
		
		// 다른 이름으로 저장
		if (result == JFileChooser.APPROVE_OPTION) {
			sourceFile = chooser.getSelectedFile();

			FileWriter fw = null;

			try {
				fw = new FileWriter(sourceFile);
				fw.write(taEditor.getText());
				fw.flush();
				saveCheck = true;
				setTitle(title + sourceFile.getName());

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				MyUtils.closeAll(fw);
			}
		}
	}

	private void saveEvent() {

		if (sourceFile != null) {
			FileWriter fw = null;
			
			try {
				
				fw = new FileWriter(sourceFile);
				fw.write(taEditor.getText());
				setTitle(title + sourceFile.getName());
				saveCheck = true;
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				MyUtils.closeAll(fw);
			}
		} else if (modifier != false) {
			saveAsEvent();
		} else if (modifier == false && sourceFile == null) {
			saveAsEvent();
		}
	}

	private void newFileEvent() {
		// 수정한 내역이 있으면
		if (modifier != false) {
			int result = JOptionPane.showConfirmDialog(this, "저장하고 새 창 ??", "알림", JOptionPane.YES_NO_OPTION);

			if (result == IS_OK) {
				saveEvent();
				newFile();
			} else {
				newFile();
			}
		} else {
			newFile();
		}
	}

	private void newFile() {
		JOptionPane.showMessageDialog(this, "새 창을 엽니다.");
		sourceFile = null;
		taEditor.setText("");
		setTitle(title);
	}

	private void openEvent() {
		int result = chooser.showOpenDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {
			sourceFile = chooser.getSelectedFile();

			FileReader fr = null;
			BufferedReader br = null;

			try {
				fr = new FileReader(sourceFile);
				br = new BufferedReader(fr);

				String line = null;
				StringBuilder builder = new StringBuilder();
				while ((line = br.readLine()) != null) {
					builder.append(line + "\n");
				}

				taEditor.setText(builder.toString());
				setTitle(title + sourceFile.getName());
				fileCheck = true;

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				MyUtils.closeAll(br, fr);
			}
		}
	}

	private void showFrame() {
		setTitle("SimplePad ver 1.0");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new SimplaPad();
	}
}
