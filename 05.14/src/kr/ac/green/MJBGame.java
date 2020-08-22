package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class MJBGame extends JFrame {

	public static final int WIN_CASE1 = -1;
	public static final int WIN_CASE2 = 2;

	private JRadioButton[] rbtns = { new JRadioButton("¹¬"), new JRadioButton("Âî"), new JRadioButton("ºü") };

	private JButton btnStart;
	private JTextArea taResult;

	public MJBGame() {
		init();
		setDisplay();
		addListeners();
		showFrame();

	}

	private void init() {
		btnStart = new JButton("½ÃÀÛ");
		taResult = new JTextArea(4, 20);
		taResult.setEditable(false);

		ButtonGroup bg = new ButtonGroup();
		for (JRadioButton rbtn : rbtns) {
			bg.add(rbtn);
		}

	}

	private void setDisplay() {
		JPanel pnlNorth = new JPanel(new GridLayout(1, 3));
		for (JRadioButton rbtn : rbtns) {
			pnlNorth.add(rbtn);
		}
		pnlNorth.setBorder(new EmptyBorder(0, 25, 0, 0));
		JPanel pnlCenter = new JPanel();
		pnlCenter.add(btnStart);

		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(new JScrollPane(taResult), BorderLayout.SOUTH);

	}

	private void addListeners() {
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int userSelect = 0;
				for (int idx = 0; idx < rbtns.length; idx++) {
					if (rbtns[idx].isSelected()) {
						userSelect = idx;
					}
				}

				int computer = (int) (Math.random() * 3);

				int result = userSelect - computer;
				StringBuffer buf = new StringBuffer();
				buf.append("ÄÄÇ»ÅÍ : ");
				buf.append(whatIsChoice(computer));
				buf.append("\n");
				buf.append("´ç½Å : ");
				buf.append(whatIsChoice(userSelect));
				buf.append("\n");
				if (computer == userSelect) {
					buf.append("ºñ°å¾î¿ä~");
				} else if (result == WIN_CASE1 || result == WIN_CASE2) {
					buf.append("´ç½ÅÀÌ ÀÌ°å¾î¿ä~");
				} else {
					buf.append("´ç½ÅÀÌ Á³À½");
				}

				taResult.setText(buf.toString());
			}
		});
	}

	private String whatIsChoice(int choice) {
		return rbtns[choice].getText();
	}

	private void showFrame() {
		setTitle("¹¬Âîºü");
		setSize(200, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	public static void main(String[] args) {
		new MJBGame();
	}
}