package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopupEx extends JFrame {
	private JPopupMenu pMenu;
	private JLabel lblMain;

	public PopupEx() {

		lblMain = new JLabel("Start : ", JLabel.CENTER);
		lblMain.setFont(new Font(Font.SERIF, Font.BOLD, 40));

		pMenu = new JPopupMenu();
		JMenuItem miApple = new JMenuItem("Apple");
		JMenuItem miBanana = new JMenuItem("Banana");
		JMenuItem miKiwi = new JMenuItem("Kiwi");

		pMenu.add(miApple);
		pMenu.addSeparator();
		pMenu.add(miBanana);
		pMenu.add(miKiwi);

		add(lblMain, BorderLayout.CENTER);

		// popup menu visible (Mouse Event)
		// OS마다 팝업 신호가 다름
		lblMain.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				showPopup(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				showPopup(e);
			}

		});
		
		ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblMain.setText(e.getActionCommand());
			}
		};
		
		miApple.addActionListener(actionListener);
		miBanana.addActionListener(actionListener);
		miKiwi.addActionListener(actionListener);

		setTitle("팝업메뉴");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void showPopup(MouseEvent me) {
		// 버튼 + 시점 판별(팝업 신호가 맞습니까?)
		if (me.isPopupTrigger()) {
			pMenu.show(lblMain, me.getX(), me.getY());
		}
	}

	public static void main(String[] args) {
		new PopupEx();
	}
}
