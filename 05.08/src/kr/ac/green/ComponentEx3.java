package kr.ac.green;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ComponentEx3 extends JFrame {
	// 선택
	public ComponentEx3() {
		setLayout(new GridLayout(1, 0));

		JPanel pnlHobby = new JPanel(new GridLayout(0, 1));

		// 다중 선택
		String[] hobbys = { "낮잠", "서핑", "게임", "공부", "독서" };
		JCheckBox[] chHobby = new JCheckBox[hobbys.length];

		for (int i = 0; i < chHobby.length; i++) {
			chHobby[i] = new JCheckBox(hobbys[i], true);
			pnlHobby.add(chHobby[i]);
		}

		JPanel pnlGrade = new JPanel(new GridLayout(0, 1));
		String[] names = { "A", "B", "C", "D" };
		JRadioButton[] rbtns = new JRadioButton[names.length];

		// 논리적인 그룹핑 ( group 안에 포함되어 있는 놈들은 하나만 선택됨 )
		ButtonGroup group = new ButtonGroup();

		for (int i = 0; i < names.length; i++) {
			rbtns[i] = new JRadioButton(names[i]);
			rbtns[i].setBackground(Color.RED);
			pnlGrade.add(rbtns[i]);
			group.add(rbtns[i]);
		}

		rbtns[0].setSelected(true);

		String[] menu = { "짜장면", "짬뽕", "탕수육", "칠리새우" };
		JComboBox cbMenu = new JComboBox(menu);
		JPanel pnlMenu = new JPanel();
		pnlMenu.add(cbMenu);

		cbMenu.setSelectedIndex(2);

		System.out.println(cbMenu.getSelectedItem());

		add(pnlHobby);
		add(pnlGrade);
		add(pnlMenu);

		setTitle("Component");
		setSize(500, 200);
		setLocation(100, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		/*
		 * for(JCheckBox temp : chHobby) { System.out.println(temp.getText() +
		 * " : " + temp.isSelected()); }
		 */
	}

	public static void main(String[] args) {
		new ComponentEx3();
	}
}
