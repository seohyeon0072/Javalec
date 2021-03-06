package kr.ac.green;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ComponentEx2 extends JFrame {
	
	public ComponentEx2() {
		//舘庚 脊径
		JTextField tfName = new JTextField(20);
		tfName.setText("食奄拭 脊径馬室推");
		//tfName.setEditable(false); (畷増 食採)
		
		//焼森 公 床惟 幻給 (醗失鉢 食採)
		tfName.setEnabled(false);
		
		JLabel lblName = new JLabel("戚硯");
		
		JPanel pnlName = new JPanel();
		pnlName.add(lblName);
		pnlName.add(tfName);
		
		//舌庚 脊径(什滴継 戚櫛 旭戚 床績)
		JTextArea taInput = new JTextArea(6, 30);
		taInput.setText("せせせせせせせせせせせ");
		taInput.append("\n馬馬馬馬");
		
		//切疑 匝 郊嘩
		taInput.setLineWrap(true);
		//切疑 匝 郊嘩拝 凶 嬢恐 奄層生稽 郊蝦 依昔亜 (焼掘澗 舘嬢奄層)
		taInput.setWrapStyleWord(true);
		//什滴継 含焼爽壱 粛精 依聖 持失切稽 穿含
		//鳶確昔汽 什滴継 郊亜 含形赤澗 依...
		JScrollPane scroll = new JScrollPane(taInput);
		//什滴継 郊研 情薦 承 依昔亜?
		
		/*
			牌雌 妊獣
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
			琶推拝 凶幻 妊獣
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

			什滴継郊 妊獣舛奪 衣舛
		*/
		
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPasswordField pwPass = new JPasswordField();
		pwPass.setText("hello");
		
		//鳶什趨球 但聖 脊径梅聖 凶 左食爽澗 依..
		pwPass.setEchoChar('*');
		char[] password = pwPass.getPassword();
		//庚切壕伸 -> 什闘元
		System.out.println(String.valueOf(password));
		
		System.out.println(password);
		
		
		add(pnlName, BorderLayout.NORTH);
		//add(taInput, BorderLayout.CENTER);
		add(scroll, BorderLayout.CENTER);
		add(pwPass, BorderLayout.SOUTH);
		
		setTitle("Component2");
		setSize(400,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new ComponentEx2();
	}
}
