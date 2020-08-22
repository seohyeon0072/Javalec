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
		//단문 입력
		JTextField tfName = new JTextField(20);
		tfName.setText("여기에 입력하세요");
		//tfName.setEditable(false); (편집 여부)
		
		//아예 못 쓰게 만듬 (활성화 여부)
		tfName.setEnabled(false);
		
		JLabel lblName = new JLabel("이름");
		
		JPanel pnlName = new JPanel();
		pnlName.add(lblName);
		pnlName.add(tfName);
		
		//장문 입력(스크롤 이랑 같이 쓰임)
		JTextArea taInput = new JTextArea(6, 30);
		taInput.setText("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
		taInput.append("\n하하하하");
		
		//자동 줄 바꿈
		taInput.setLineWrap(true);
		//자동 줄 바꿈할 때 어떤 기준으로 바꿀 것인가 (아래는 단어기준)
		taInput.setWrapStyleWord(true);
		//스크롤 달아주고 싶은 것을 생성자로 전달
		//패널인데 스크롤 바가 달려있는 것...
		JScrollPane scroll = new JScrollPane(taInput);
		//스크롤 바를 언제 쓸 것인가?
		
		/*
			항상 표시
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
			필요할 때만 표시
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

			스크롤바 표시정책 결정
		*/
		
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPasswordField pwPass = new JPasswordField();
		pwPass.setText("hello");
		
		//패스워드 창을 입력했을 때 보여주는 것..
		pwPass.setEchoChar('*');
		char[] password = pwPass.getPassword();
		//문자배열 -> 스트링
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
