package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ItemEventEx extends JFrame {

	private JRadioButton rbtnRed;
	private JRadioButton rbtnGreen;
	private JRadioButton rbtnBlue;

	private JLabel lblColor;

	private String name;

	public ItemEventEx() {
		init();
		setDisplay();
		addListeners();
		showFrame();
	}

	private void init() {
		rbtnRed = new JRadioButton("Radio", true);
		rbtnGreen = new JRadioButton("Green");
		rbtnBlue = new JRadioButton("Blue");

		ButtonGroup radioGroup = new ButtonGroup();

		radioGroup.add(rbtnRed);
		radioGroup.add(rbtnGreen);
		radioGroup.add(rbtnBlue);

		lblColor = new JLabel();
		lblColor.setOpaque(true);
		lblColor.setBackground(Color.RED);
		lblColor.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), "Color Display"));
	}

	private void setDisplay() {
		JPanel pnl = new JPanel();

		pnl.add(rbtnRed);
		pnl.add(rbtnGreen);
		pnl.add(rbtnBlue);

		add(pnl, BorderLayout.NORTH);
		add(lblColor, BorderLayout.CENTER);
	}

	private void addListeners() {

		// ActionListener listener = (ae) -> {
		// JRadioButton btn = (JRadioButton) ae.getSource();
		// System.out.println(btn.getText());
		// };
		//
		// rbtnRed.addActionListener(listener);
		// rbtnBlue.addActionListener(listener);
		// rbtnGreen.addActionListener(listener);

		// 윈도우에 대한 리스너
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(ItemEventEx.this, "종료 할래?", "창", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				System.exit(result);
			}

			// setVisible 이후에 동작함
			// @Override
			// public void windowOpened(WindowEvent we) {
			// do {
			// name = JOptionPane.showInputDialog(
			// ItemEventEx.this ,
			// "이름을 입력해 주세요",
			// "입력",
			// JOptionPane.QUESTION_MESSAGE
			// );
			// } while(name == null || name.trim().length() == 0);
			//
			// }

		});

		///////////////////////////////
		// rbtnRed.addActionListener( (ae) -> {
		// System.out.println("RED");
		// });

		// ItemListener listener = new ItemListener() {
		// @Override
		// public void itemStateChanged(ItemEvent e) {
		//
		//// JRadioButton btn = (JRadioButton) e.getSource();
		//// String state;
		//
		// if(e.getStateChange() == ItemEvent.SELECTED) {
		// //선택되서 이벤트 발생
		// Object src = e.getSource();
		// Color color = Color.GREEN;
		// if(src == rbtnRed) {
		// color = Color.RED;
		// } else if( src == rbtnBlue) {
		// color = Color.BLUE;
		// }
		//
		// lblColor.setBackground(color);
		// }
		// }
		// };

		ItemListener listener = (e) -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				// 선택되서 이벤트 발생
				Object src = e.getSource();
				Color color = Color.GREEN;
				if (src == rbtnRed) {
					color = Color.RED;
				} else if (src == rbtnBlue) {
					color = Color.BLUE;
				}

				lblColor.setBackground(color);
			}

		};

		rbtnRed.addItemListener(listener);
		rbtnBlue.addItemListener(listener);
		rbtnGreen.addItemListener(listener);

	}

	private void showFrame() {
		setTitle(" 아이템 이벤트");
		setSize(300, 400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ItemEventEx();

	}

}
