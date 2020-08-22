package test;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RoomClient extends JDialog implements ActionListener  {
	JLabel lbl;
	JTextArea ta;
	JTextField tf;
	JScrollPane sp;
	PrintWriter pw;
	BufferedReader br;
	int soketnumber;
	String id;
	String roomname;
	ChatProgramtest chat;
	Socket socket;
	public RoomClient(ChatProgramtest chat,String title,int soketnumber, String idb , String roomname) {
		super(chat);
		this.chat = chat;
		this.id = idb;
		this.soketnumber = soketnumber;
		this.roomname = roomname;
		this.chat.setVisible(false);
		GUI();
		try {
			socket = new Socket(chat.ip,this.soketnumber);// accept랑 연결됨
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			lbl.setText(this.roomname+" "+id);
			pw.println(id);
			pw.flush();
			(new RoomClientThread(this,br, ta, pw, socket)).start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				chat.revalidate();
				chat.repaint();
				chat.setVisible(true);
				pw.println("/exit");
				pw.flush();
				try {
					br.close();
					socket.close();
				} catch (IOException e1) {
				e1.printStackTrace();
				}
				pw.close();
				dispose();
			}
		});
		Runtime.getRuntime().addShutdownHook(new Thread() {
	         public void run() {
	        	 pw.println("/exit");
					pw.flush();
					try {
						br.close();
						socket.close();
					} catch (IOException e1) {
					e1.printStackTrace();
					}
					pw.close();
	         }
	    });
	}
	void GUI() {
		lbl = new JLabel(this.roomname+" "+id);
		ta = new JTextArea();
		sp = new JScrollPane(ta);
		tf = new JTextField();
		tf.addActionListener(this);
		tf.setFocusable(true);
		this.add(lbl,"North");
		this.add(sp,"Center");
		this.add(tf,"South");
		this.setSize(400, 500);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {		
		tf.setFocusable(true);
		if(tf.getText().equals("/exit")){
			pw.println("/exit");
			pw.flush();					
			pw.close();
			try {
				br.close();
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			dispose();
		}else{
			pw.println(tf.getText()+"");
			pw.flush();			
		}
		tf.setText("");
	}
	class RoomClientThread extends Thread{
		BufferedReader br;
		JTextArea ta;
		PrintWriter pw;
		Socket sock;
		RoomClient main;
		RoomClientThread(RoomClient dialog,BufferedReader br, JTextArea ta, PrintWriter pw, Socket sock){
			main = dialog;
			this.br=br;
			this.ta=ta;
			this.pw=pw;// 쓰지 않음. 종료 시킬때 여기서 해야 함으로 부름
			this.sock=sock;// 쓰지 않음. 종료 시킬때 여기서 해야 함으로 부름
		}
		@Override
		public void run(){
			String line = null;	
			try {
				while((line = br.readLine()) != null){
					if(line.indexOf("/exit") != -1){
						String[] lineP = line.split(" ");
						if(lineP[0].equals("/exit") && lineP[1].equals(id)){
							main.chat.revalidate();
							main.chat.repaint();
							main.chat.setVisible(true);
							System.out.print("추방");
							pw.println("/exit");
							pw.flush();
							br.close();
							pw.close();
							sock.close();
							dispose();
							break;
						}
					} else {
						ta.append(line + "\n");
						ta.setCaretPosition(ta.getDocument().getLength());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {			
				try {
					if(br != null){
						br.close();
					}
					if(pw != null){
						pw.close();
					}
					if(sock != null){
						sock.close();					
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		}
	}
}
