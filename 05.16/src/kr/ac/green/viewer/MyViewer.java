package kr.ac.green.viewer;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MyViewer extends JFrame {

	private ArrayList<JLabel> list;

	public MyViewer() {
		init();
		setDisplay();
		showFrame();
	}

	public ArrayList<JLabel> getList() {
		return list;
	}

	private void init() {
		list = new ArrayList<JLabel>();

		String[] fruitList = { "apple", "asparagus", "banana", "broccoli", "cantaloupe", "carrot", "corn", "grapefruit",
				"grapes", "kiwi", "onion", "peach", "pear", "pepper", "pickle", "pineapple", "raspberry", "strawberry",
				"tomato", "watermelon" };

		addImage(fruitList);
	}

	private void setDisplay() {
		add(showImage());
	}

	// 이미지 추가
	private void addImage(String[] fruitList) {

		for (int i = 0; i < fruitList.length; i++) {
			JLabel img = new JLabel();
			img.setIcon(new ImageIcon(fruitList[i] + ".jpg"));
			img.setToolTipText("Click the " + fruitList[i]);

			img.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent me) {
						JLabel lbl = (JLabel) me.getSource();
						new ImageInfo(MyViewer.this, list.indexOf(lbl));
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					img.setBorder(new LineBorder(Color.GREEN, 2));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					img.setBorder(null);
				}

			});

			list.add(img);
		}
	}

	// 이미지 전체 보여주기
	private JPanel showImage() {
		JPanel pnl = new JPanel(new GridLayout(5, 4));

		int i = 0;
		while (i < list.size()) {
			pnl.add(list.get(i));
			i++;
		}

		return pnl;
	}

	private void showFrame() {
		setTitle("뷰어");
		setSize(400, 400);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MyViewer();
	}
}