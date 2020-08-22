package Lec4;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class ChatClient extends JFrame {
	private Socket sock;
	private BufferedReader br;
	private PrintWriter pw;
	private JTextField tfIp;
	private JTextField tfId;
	private JTextField tfInput;

	private JTextArea taOutput;

	private JButton btnSend;
	private JButton btnOut;

	private String ip;
	private String id;

	public ChatClient() {

		init();
		setDisplay();
		addListeners();
		showFrame();

		String ip = JOptionPane.showInputDialog("접속할 IP를 입력하세요 ");
		String id = JOptionPane.showInputDialog("사용할 ID를 입력하세요 ");
		if (ip.length() == 0 || id.length() == 0) {
			System.out.println("IP와 ID를 제대로 입력하지 않아 프로그램을 종료합니다.");
			System.exit(-1);
		}
		
		try {
			sock = new Socket(ip, 10001);
			pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in) 
			);
			pw.println(id);
			pw.flush();
			new Thread() {
				public void run() {
					try {
						String line = null;
						taOutput.setText("대화방에 입장하셨습니다." + "\n");
						while ((line = br.readLine()) != null) {
							if (line.equals("/quit")) {
								throw new Exception();
							}
							System.out.println(line);
    
							taOutput.append(line + "\n"); 
						}
					} catch (Exception e) {
						taOutput.append("대화가 종료 되었습니다" + "\n");
						tfInput.setEditable(false);
					} finally {
					}
				}
			}.start();
			String line = null;
			while ((line = keyboard.readLine()) != null) {
				pw.println(line);
				pw.flush();
			}
		} catch (Exception e) {
			System.out.println(e);

		} finally {
			exit();
		}

	}

	private void init() {
		tfInput = new JTextField(15);
		taOutput = new JTextArea(20, 30);
		taOutput.setLineWrap(true);
		taOutput.grabFocus();
		btnSend = new JButton("전송");

		btnOut = new JButton("나가기");
	}

	private void setDisplay() {
		JPanel pnlNorth = new JPanel();
		JPanel pnlCenter = new JPanel();
		JPanel pnlSouth = new JPanel();

		pnlCenter.add(taOutput);

		pnlSouth.add(tfInput);
		pnlSouth.add(btnSend);
		pnlSouth.add(btnOut);

		add(pnlNorth, BorderLayout.NORTH);
		add(new JScrollPane(pnlCenter), BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}

	private void addListeners() {
		// TODO Auto-generated method stub
		btnSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pw.println(tfInput.getText());
				pw.flush();
				tfInput.setText("");
			}
		});

		tfInput.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_ENTER) {

					pw.println(tfInput.getText());
					pw.flush();
					tfInput.setText("");
				}
			}
		});
		
		btnOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
	}

	private void showFrame() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private void exit() {
		try {
			if (pw != null) {
				pw.close();
			}
		} catch (Exception e) {
		}
		try {
			if (sock != null)
			sock.close();
		} catch (Exception e) {
		}

		System.out.println("클라이언트의 접속을 종료합니다.");
		System.exit(0);
	}

	public static void main(String[] args) {
		new ChatClient();
	}
}