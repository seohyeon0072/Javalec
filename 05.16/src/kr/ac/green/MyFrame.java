package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/*
 * 새창 띄우기 - JDialog, JFrame (일반적으로 JDialog)
 *  
 */
public class MyFrame extends JFrame {

	// private JButton btnOpen;
	private Vector<String> wordsList;
	private JTextArea taList;

	public MyFrame() {
		// btnOpen = new JButton("Open");
		// 무조건 BorderLayout North, East, West, South 가 비어 이어야 한다.

		wordsList = new Vector<String>();

		JToolBar tBar = new JToolBar();
		tBar.setFloatable(false);

		JLabel lblInput = new JLabel("입력 : ");
		JTextField tfInput = new JTextField(10);
		tfInput.setEditable(false);
		JButton btnInput = new JButton("입력");
		JButton btnList = new JButton("목록");
		JPanel pnlInput = new JPanel();
		pnlInput.add(lblInput);
		pnlInput.add(tfInput);
		pnlInput.add(btnInput);
		pnlInput.add(btnList);

		tBar.add(new JButton("Open"));
		tBar.add(new JButton("Save"));
		tBar.addSeparator();
		tBar.add(new JButton("Exit"));
		tBar.add(Box.createHorizontalGlue());
		tBar.add(new JButton("Help"));
		
		taList = new JTextArea(5, 25);
		taList.setEditable(false);
		JScrollPane scroll = new JScrollPane(taList);
		scroll.setBorder(new TitledBorder(
				new LineBorder(Color.GRAY, 1) ,
				"List"));
		

		JPanel pnl = new JPanel();
		JPanel pnlCenter = new JPanel(new BorderLayout());

		// pnl.add(btnOpen);
		pnlCenter.add(pnl, BorderLayout.SOUTH);
		pnlCenter.add(pnlInput, BorderLayout.CENTER);
		pnlCenter.add(scroll, BorderLayout.SOUTH);
		
		add(tBar, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);

		// btnOpen.addActionListener((ae) -> new MyDialog(MyFrame.this));
		btnInput.addActionListener((ae) -> new MyDialog(MyFrame.this));
		btnList.addActionListener((ae) -> {
			taList.setText("");
			for(String word : wordsList) {
				taList.append(word + "\n");
			}
		});
		
		
		setSize(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}
	/*
	 * wordsList 에 존재여부 확인
	 */
	public boolean exists(String words) {
		return wordsList.contains(words);
	}
	
	public void add(String words) {
		wordsList.add(words);
	}

	public static void main(String[] args) {
		new MyFrame();
	}

}
