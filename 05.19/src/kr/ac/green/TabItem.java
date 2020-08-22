package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TabItem extends JPanel {
	private char a = 'A';

	public TabItem() {
		setLayout(new BorderLayout());

		JLabel lbl = new JLabel(String.valueOf(a), JLabel.CENTER);
		lbl.setFont(new Font(Font.SERIF, Font.BOLD, 60));

		add(lbl, BorderLayout.CENTER);

	}

}
