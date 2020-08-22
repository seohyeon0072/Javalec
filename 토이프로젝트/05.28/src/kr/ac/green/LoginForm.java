package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame {
	private JLabel lblId;
	private JLabel lblPw;

	private JTextField tfInputId;
	private JPasswordField tfInputPw;

	private JButton btnLogin;
	private JButton btnJoin;

	private LoginControl control;

	public LoginForm() {
		init();
		setDisplay();
		addEvent();
		showFrame();
	}

	private void init() {
		Dimension btnSize = new Dimension(90, 25);
		control = new LoginControl();
		lblId = new JLabel("ID");
		lblPw = new JLabel("Password");

		tfInputId = new JTextField(10);
		tfInputPw = new JPasswordField(10);

		btnLogin = new JButton("Login");
		btnLogin.setPreferredSize(btnSize);

		btnJoin = new JButton("Join");
		btnJoin.setPreferredSize(btnSize);
	}

	private void setDisplay() {
		JPanel pnlLeft = new JPanel(new GridLayout(0, 1));

		JPanel pnlLeftItem1 = new JPanel();
		pnlLeftItem1.add(lblId);

		JPanel pnlLeftItem2 = new JPanel();
		pnlLeftItem2.add(lblPw);

		pnlLeft.add(pnlLeftItem1);
		pnlLeft.add(pnlLeftItem2);

		JPanel pnlRight = new JPanel(new GridLayout(0, 1));
		JPanel pnlRightItem1 = new JPanel();
		pnlRightItem1.add(tfInputId);

		JPanel pnlRightItem2 = new JPanel();
		pnlRightItem2.add(tfInputPw);

		pnlRight.add(pnlRightItem1);
		pnlRight.add(pnlRightItem2);

		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnLogin);
		pnlSouth.add(btnJoin);

		add(pnlLeft, BorderLayout.WEST);
		add(pnlRight, BorderLayout.EAST);
		add(pnlSouth, BorderLayout.SOUTH);

	}

	private void addEvent() {
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new JoinForm();
			}
		});

		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				String userId = tfInputId.getText();
				String userPw = String.valueOf(tfInputPw.getPassword());
				
				try {
					MemberDTO user = control.userLogin(userId, userPw);
					if (user != null) {
						new Infomation(user);
						tfInputId.setText("");
						tfInputPw.setText("");
					} else {
						throw new IdCheckException(userId, userPw);
					}
				} catch (IdCheckException e) {
					JOptionPane.showMessageDialog(LoginForm.this, e.getMessage());
				}
			}
		});
	}

	private void showFrame() {
		setTitle("Login");
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
