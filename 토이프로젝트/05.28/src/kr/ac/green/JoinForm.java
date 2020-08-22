package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout.Group;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class JoinForm extends JFrame {
	public static final Dimension btnSize = new Dimension(100, 25);

	private LoginControl control;

	private JLabel lblInfo;
	private JLabel lblId;
	private JLabel lblPw;
	private JLabel lblRetry;
	private JLabel lblName;
	private JLabel lblNickName;

	private JTextField tfInputId;
	private JPasswordField tfInputPw;
	private JPasswordField tfInputPwRetry;
	private JTextField tfName;
	private JTextField tfNickName;

	private JRadioButton rbtMale;
	private JRadioButton rbtFemale;

	private ButtonGroup group;
	private JButton btnJoin;
	private JButton btnCancel;

	public JoinForm() {
		control = new LoginControl();
		init();
		setDisplay();
		addEvent();
		showFrame();
	}

	private void init() {

		lblInfo = new JLabel(" - Input your information", JLabel.CENTER);

		lblId = new JLabel("ID");
		lblPw = new JLabel("Password");
		lblRetry = new JLabel("Retry");
		lblName = new JLabel("Name");
		lblNickName = new JLabel("NickName");

		rbtMale = new JRadioButton("Male");
		rbtMale.setSelected(true);
		rbtFemale = new JRadioButton("Female");

		btnJoin = new JButton("Join");
		btnJoin.setPreferredSize(btnSize);

		btnCancel = new JButton("Cancel");
		btnCancel.setPreferredSize(btnSize);

		tfInputId = new JTextField(10);
		tfInputPw = new JPasswordField(10);
		tfInputPwRetry = new JPasswordField(10);
		tfName = new JTextField(10);
		tfNickName = new JTextField(10);
	}

	private void setDisplay() {
		JPanel pnlTop = new JPanel(new BorderLayout());

		JPanel pnlLeft = new JPanel(new GridLayout(0, 1));
		JPanel pnlLeftItem1 = new JPanel();
		pnlLeftItem1.add(lblId);

		JPanel pnlLeftItem2 = new JPanel();
		pnlLeftItem2.add(lblPw);

		JPanel pnlLeftItem3 = new JPanel();
		pnlLeftItem3.add(lblRetry);

		JPanel pnlLeftItem4 = new JPanel();
		pnlLeftItem4.add(lblName);

		JPanel pnlLeftItem5 = new JPanel();
		pnlLeftItem5.add(lblNickName);

		pnlLeft.add(pnlLeftItem1);
		pnlLeft.add(pnlLeftItem2);
		pnlLeft.add(pnlLeftItem3);
		pnlLeft.add(pnlLeftItem4);
		pnlLeft.add(pnlLeftItem5);

		JPanel pnlRight = new JPanel(new GridLayout(0, 1));

		JPanel pnlRightItem1 = new JPanel();
		pnlRightItem1.add(tfInputId);

		JPanel pnlRightItem2 = new JPanel();
		pnlRightItem2.add(tfInputPw);

		JPanel pnlRightItem3 = new JPanel();
		pnlRightItem3.add(tfInputPwRetry);

		JPanel pnlRightItem4 = new JPanel();
		pnlRightItem4.add(tfName);

		JPanel pnlRightItem5 = new JPanel();
		pnlRightItem5.add(tfNickName);

		pnlRight.add(pnlRightItem1);
		pnlRight.add(pnlRightItem2);
		pnlRight.add(pnlRightItem3);
		pnlRight.add(pnlRightItem4);
		pnlRight.add(pnlRightItem5);

		pnlTop.add(lblInfo, BorderLayout.NORTH);
		pnlTop.add(pnlLeft, BorderLayout.WEST);
		pnlTop.add(pnlRight, BorderLayout.EAST);

		JPanel pnlSouth = new JPanel(new BorderLayout());
		JPanel pnlSouthItem2 = new JPanel();
		pnlSouthItem2.add(btnJoin);
		pnlSouthItem2.add(btnCancel);

		pnlSouth.add(pnlSouthItem2, BorderLayout.SOUTH);

		group = new ButtonGroup();
		group.add(rbtMale);
		group.add(rbtFemale);

		JPanel pnlRadio = new JPanel();
		JPanel pnlRadioItem = new JPanel();

		pnlRadioItem.add(rbtMale);
		pnlRadioItem.add(rbtFemale);

		TitledBorder t = new TitledBorder(new LineBorder(Color.GRAY, 1));
		t.setTitle("Gender");

		pnlRadioItem.setBorder(t);
		pnlRadioItem.setPreferredSize(new Dimension(200, 60));
		pnlRadio.add(pnlRadioItem);

		pnlSouth.add(pnlRadio, BorderLayout.CENTER);

		add(pnlTop, BorderLayout.NORTH);
		add(pnlSouth, BorderLayout.CENTER);
	}

	private void addEvent() {
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					boolean check = false;
					if (userValidate()) {
						MemberDTO userInfo = getUserInfo();
						String id = userInfo.getId();
						System.out.println("ÀÔ·ÂÇÑ ¾ÆÀÌµð :" + id);
						control.idOverlapCheck(id);
						check = control.userJoin(userInfo);
					}

					if (check) {
						JOptionPane.showMessageDialog(JoinForm.this, "È¸¿ø°¡ÀÔÀ» È¯¿µÇÕ´Ï´Ù", "¾È³»",
								JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
					}

				} catch (IdCheckException er) {
					JOptionPane.showMessageDialog(JoinForm.this, "ÀÌ¹Ì Á¸ÀçÇÏ´Â ¾ÆÀÌµð ÀÔ´Ï´Ù.\n´Ù½Ã ÀÔ·Â ¹Ù¶÷", "¾Ë¸²",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	// ºñ¹Ð¹øÈ£ µÎ °¡Áö °°ÀºÁö °Ë»ç
	private boolean pwCheck(String userPw, String userPwRetry) {
		if (userPw.equals(userPwRetry)) {
			return true;
		}
		return false;
	}

	// ÀÌ¸§ °Ë»ç 2±ÛÀÚ ÀÌ»ó + ÇÑ±Û
	private boolean nameCheck(String userName) {
		// Á¤±Ô Ç¥Çö½Ä..
		String regex = "^[¤¡-¤¾°¡-ÆR]*$";

		if (userName.matches(regex)) {
			return true;
		} else {
			return false;
		}
	}

	// ¾ÆÀÌµð °Ë»ç
	private boolean idCheck(String userId) {
		char[] temp = userId.toCharArray();
		for (int i = 0; i <= temp.length; i++) {
			if ((temp[i] >= 0 || temp[i] <= 9) && (temp[i] >= 97 || temp[i] <= 122)) {
				return true;
			}
		}
		return false;
	}

	// ÀÔ·ÂÇÑ Á¤º¸¸¦ Á¦´ë·Î ÀÔ·Â Çß´ÂÁö °Ë»ç
	private boolean userValidate() {
		// 1. ¾ÆÀÌµð ÇÑ±Û X 6~12ÀÚ
		// 2. ºñ¹Ð¹øÈ£ == ºñ¹Ð¹øÈ£ È®ÀÎ
		// 3. ÀÌ¸§ ¿µ¾î + ÇÑ±Û
		// 4. ´Ð³×ÀÓ ¿µ¾î + ÇÑ±Û
		// 5. ¼ºº°Àº Ã¼Å© µÇ¾î ÀÖ´ÂÁö¸¸ °Ë»ç
		try {

			String userId = tfInputId.getText();
			int idSize = userId.length();
			if ((idSize >= 6 && idSize <= 12)) {
				if (idCheck(userId) == false) {
					throw new IdFormatException();
				}
			} else {
				throw new IdCheckException();
			}

			String userPw = tfInputPw.getText();
			String userPwRetry = tfInputPwRetry.getText();

			if (userPw.equals("") || userPwRetry.equals("")) {
				throw new PasswordCheckException(0);
			}
			if (!(pwCheck(userPw, userPwRetry))) {
				throw new PasswordCheckException();
			}

			String userName = tfName.getText();
			if (userName.length() <= 1) {
				throw new NameCheckException(2);
			}
			if (!(nameCheck(userName))) {
				throw new NameCheckException();
			}

			String nickName = tfNickName.getText();
			if (nickName.length() < 2) {
				throw new NickNameCheckException();
			}

			return true;

		} catch (IdCheckException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (IdFormatException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (PasswordCheckException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (NameCheckException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (NickNameCheckException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

		return false;
	}

	// ÀÔ·ÂÇÑ È¸¿øÀÇ µ¥ÀÌÅÍ¸¦ °¡Á®¿À±â
	private MemberDTO getUserInfo() {
		MemberDTO user = new MemberDTO();

		user.setId(tfInputId.getText());
		user.setPw(tfInputPw.getText());
		user.setName(tfName.getText());
		user.setNickName(tfNickName.getText());

		if (rbtMale.isSelected()) {
			user.setGender(rbtMale.getText());
		} else {
			user.setGender(rbtFemale.getText());
		}

		return user;
	}

	private void showFrame() {
		setTitle("Join");
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
