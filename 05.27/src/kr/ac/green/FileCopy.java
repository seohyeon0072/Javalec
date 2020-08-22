package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class FileCopy extends JFrame {
	private int fileInputSize = 20;

	private JTextField tfSrc;
	private JTextField tfCopy;

	private JButton btnSrc;
	private JButton btnCopy;

	private JTextField tfSize;

	private JButton btnRun;

	private JTextArea taResult;

	private JFileChooser chooser;

	private File srcFile;
	private File copyFile;

	public FileCopy() {
		init();
		setDisplay();
		addListeners();
		showFrame();
	}

	private void init() {
		tfSrc = new JTextField(fileInputSize);
		tfSrc.setEditable(false);
		tfSrc.setBorder(new LineBorder(Color.GRAY, 1));
		tfCopy = new JTextField(fileInputSize);
		tfCopy.setEditable(false);
		tfCopy.setBorder(new LineBorder(Color.GRAY, 1));

		btnSrc = new JButton("선택");
		btnCopy = new JButton("선택");

		tfSize = new JTextField(25);
		tfSize.setHorizontalAlignment(JTextField.CENTER);

		btnRun = new JButton("복사");

		taResult = new JTextArea(7, 25);
		taResult.setEditable(false);

		chooser = new JFileChooser("c:\\");
	}

	private void setDisplay() {
		JPanel pnlNorth = new JPanel(new GridLayout(0, 1));

		JPanel pnlSrc = new JPanel();
		pnlSrc.add(tfSrc);
		pnlSrc.add(btnSrc);

		setTitledBorder(pnlSrc, "원본정보");

		JPanel pnlCopy = new JPanel();
		pnlCopy.add(tfCopy);
		pnlCopy.add(btnCopy);

		setTitledBorder(pnlCopy, "복사본정보");

		pnlNorth.add(pnlSrc);
		pnlNorth.add(pnlCopy);

		JPanel pnlCenter = new JPanel(new BorderLayout());

		JPanel pnlSize = new JPanel();
		pnlSize.add(tfSize);
		setTitledBorder(pnlSize, "버퍼크기");

		JPanel pnlRun = new JPanel();
		pnlRun.add(btnRun);

		JScrollPane scroll = new JScrollPane(taResult);
		setTitledBorder(scroll, "실행결과");

		pnlCenter.add(pnlSize, BorderLayout.NORTH);
		pnlCenter.add(pnlRun, BorderLayout.CENTER);
		pnlCenter.add(scroll, BorderLayout.SOUTH);

		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);

	}

	private void setTitledBorder(JComponent cmp, String title) {
		cmp.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), title));
	}

	private void addListeners() {
		ActionListener aListner = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object src = ae.getSource();
				if (src == btnSrc) {
					selectSrc();
				} else if (src == btnCopy) {
					selectCopy();
				} else {
					copy();
				}
			}
		};

		btnSrc.addActionListener(aListner);
		btnCopy.addActionListener(aListner);
		btnRun.addActionListener(aListner);
	}

	private void showFrame() {
		setTitle("파일복사");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void copy() {
		boolean flag = true;
		if (srcFile == null) {
			JOptionPane.showMessageDialog(this, "원본파일을 선택하시오");
			flag = false;
		}

		if (flag && copyFile == null) {
			JOptionPane.showMessageDialog(this, "복사본파일을 선택하시오");
			flag = false;
		}

		String size = tfSize.getText();
		if (flag && size.trim().length() == 0) {
			JOptionPane.showMessageDialog(this, "버퍼크기를 입력가세요");
			flag = false;
		}

		int bufferSize = 0;
		if (flag) {
			try {
				bufferSize = Integer.parseInt(size);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "버퍼의 크기는 숫자만 입력하세요");
				flag = false;
			}
		}

		if (flag) {
			FileInputStream fis = null;
			FileOutputStream fos = null;

			try {
				fis = new FileInputStream(srcFile);
				fos = new FileOutputStream(copyFile);

				int count = -1;
				byte[] buffer = new byte[bufferSize];
				long time = System.currentTimeMillis();
				while ((count = fis.read(buffer)) != -1) {
					fos.write(buffer, 0, count);
				}
				time = System.currentTimeMillis() - time;
				JOptionPane.showMessageDialog(this, "복사완료");

				StringBuffer buf = new StringBuffer();
				buf.append("- 작업 정보 -\n");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd(a hh:mm:ss)");

				buf.append("- 작업 일시 :" + sdf.format(new Date()) + "\n");
				buf.append("원본 파일 : " + srcFile.getPath() + "\n");
				buf.append("복사본 파일 : " + copyFile.getPath() + "\n");
				buf.append("- 파일 크기  : " + srcFile.length() + "bytes\n");
				buf.append("- 소요 시간 :" + time + " ms");
				
				taResult.setText(buf.toString());

			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "복사중 오류가 발생");
			} finally {
				MyUtils.closeAll(fis, fos);
			}

		}
	}

	private void selectCopy() {
		int result = chooser.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			copyFile = chooser.getSelectedFile();
			tfCopy.setText(copyFile.getPath());
		}
	}

	private void selectSrc() {

		int result = chooser.showOpenDialog(this);

		// 선택하고 확인 눌렀을 때
		if (result == JFileChooser.APPROVE_OPTION) {
			srcFile = chooser.getSelectedFile();
			tfSrc.setText(srcFile.getPath());
		}
	}

	public static void main(String[] args) {
		new FileCopy();
	}
}