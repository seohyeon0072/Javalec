package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Infomation extends JFrame {
	public static final Dimension btnSize = new Dimension(100, 25);

	private JTextArea taInfo;

	private JButton btnLogout;
	private JButton btnWithDraw;
	
	private MemberDTO user;

	public Infomation() {
		init();
		setDisplay();
		addEvent();
		showFrame();
	}

	public Infomation(MemberDTO user) {
		this();
		this.user = user;
		taInfo.setText(this.user.toString());
	}

	private void init() {
		taInfo = new JTextArea(10, 40);
		taInfo.setEnabled(false);
		btnLogout = new JButton("Logout");
		btnLogout.setPreferredSize(btnSize);

		btnWithDraw = new JButton("Withdraw");
		btnWithDraw.setPreferredSize(btnSize);
	}

	private void setDisplay() {
		TitledBorder t = new TitledBorder(new LineBorder(Color.GRAY, 1));
		t.setTitle("Check yout Information");

		JPanel pnlCenter = new JPanel();
		pnlCenter.setOpaque(true);
		pnlCenter.setBackground(Color.WHITE);
		pnlCenter.setBorder(t);

		JPanel pnlCenterItem = new JPanel();
		pnlCenterItem.setOpaque(true);
		pnlCenterItem.setBackground(Color.WHITE);
		pnlCenterItem.add(taInfo);

		pnlCenter.add(pnlCenterItem);

		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnLogout);
		pnlSouth.add(btnWithDraw);

		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

	}

	private void addEvent() {
		btnWithDraw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginControl control = new LoginControl();
				
				control.withDraw(user);
				JOptionPane.showMessageDialog(Infomation.this, "È¸¿ø Å»Åð");
				setVisible(false);
			}
		});

		btnLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
			}
		});
	}

	private void showFrame() {
		setTitle("Information");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

}
