package kr.ac.green.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;

public class ImageInfo extends JDialog {

	private MyViewer imageInfo;
	private int number;

	private JLabel lblTop;

	private JLabel lblImage;

	private JPopupMenu pMenu;
	private JMenuItem pNext;
	private JMenuItem pPrev;

	public ImageInfo(MyViewer imageInfo, int number) {
		this.imageInfo = imageInfo;
		this.number = number;
		init();

		setDisplay();
		addEvent();

		showFrame();
	}

	private void init() {
		pMenu = new JPopupMenu();
		pNext = new JMenuItem("앞으로");
		pPrev = new JMenuItem("뒤로");

		String str = new String(number + 1 + " / " + imageInfo.getList().size());
		lblTop = new JLabel(str, JLabel.CENTER);

		lblImage = new JLabel(new ImageIcon(reSizeImg(this.number)));

	}

	private void setDisplay() {
		pMenu.add(pNext);
		pMenu.add(pPrev);

		lblImage.setBorder(new LineBorder(Color.GREEN, 2));

		JPanel pnlCenter = new JPanel();
		pnlCenter.add(lblImage);

		add(lblTop, BorderLayout.NORTH);

		add(pnlCenter, BorderLayout.CENTER);
	}

	private void showFrame() {
		setTitle("Do you see?");
		setSize(400, 400);
		setResizable(false);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void addEvent() {
		lblImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);
				try {
					if (me.getX() < lblImage.getWidth() / 2) {
						number--;
						imageChange();
					} else {
						number++;
						imageChange();
					}

				} catch (IndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(ImageInfo.this, "할 수 없습니다.", "알림", JOptionPane.QUESTION_MESSAGE);
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				showPopup(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				showPopup(e);
			}

		});

		ActionListener ae = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String str = e.getActionCommand();
				if (str.equals("앞으로")) {
					number++;
					imageChange();
				} else {
					number--;
					imageChange();
				}

			}
		};

		pNext.addActionListener(ae);
		pPrev.addActionListener(ae);
	}

	private Image reSizeImg(int number) {
		// 이미지 가져와서 resize
		Icon temp = imageInfo.getList().get(number).getIcon();

		Image img = Toolkit.getDefaultToolkit().getImage(temp.toString());
		Image newSize = img.getScaledInstance(400, 400, Image.SCALE_SMOOTH);

		return newSize;
	}

	private void imageChange() {

		lblImage.setIcon(new ImageIcon(reSizeImg(number)));
		lblTop.setText(number + 1 + " / " + imageInfo.getList().size());
	}

	private void showPopup(MouseEvent e) {
		if (e.isPopupTrigger()) {
			pMenu.show(lblImage, e.getX(), e.getY());
		}
	}
}
