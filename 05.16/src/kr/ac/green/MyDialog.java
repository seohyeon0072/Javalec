package kr.ac.green;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * JDialog : 창을 정의하는 클래스.
 * 1. 메뉴를 가질 수 없음
 * 2. base container
 * 3. 기본 레이아웃 매니저 : BorderLayout
 * 4. modal 설정 가능 : 부모창의 기능동작 정지
 */

public class MyDialog extends JDialog {

	private JButton btnClose;
	private MyFrame owner;

	public MyDialog(MyFrame owner) {
		// 부모창 설정 , 타이틀 , modal
		super(owner, "MyDialog", true);
		this.owner = owner;

		JLabel lblInput = new JLabel("입력 : ");
		JTextField tfInput = new JTextField(15);
		JButton btnInput = new JButton("사용");

		JPanel pnlCenter = new JPanel();
		pnlCenter.add(lblInput);
		pnlCenter.add(tfInput);
		pnlCenter.add(btnInput);

		btnClose = new JButton("Close");
		btnInput.addActionListener((ae) -> {
			// 1.7 까지는 MyFrame 지역변수로 못 쓴다.
			String words = tfInput.getText();
			if (owner.exists(words)) {
				JOptionPane.showMessageDialog(MyDialog.this, "이미 존재 합니다", "알림", JOptionPane.WARNING_MESSAGE);
				tfInput.requestFocus();
				tfInput.selectAll();

			} else {
				owner.add(words);
				dispose();
			}

		});

		JPanel pnl = new JPanel();
		pnl.add(btnClose);

		add(pnlCenter, BorderLayout.CENTER);
		add(pnl, BorderLayout.SOUTH);

		// 종료
		btnClose.addActionListener((ae) -> dispose());

		setTitle("다이얼로그");
		setSize(200, 200);
		// 부모 창의 정 중앙 위치
		setLocationRelativeTo(owner);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}

}
