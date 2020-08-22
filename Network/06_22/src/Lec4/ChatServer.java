package Lec4;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class ChatServer extends JFrame{
	
	private JTextField txtMain;
	
	public ChatServer() {
		init();
		setDisplay();
		showFrame();
	}
	
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(10001);
			System.out.println("접속기다린다");

			HashMap<String,PrintWriter> hm = 
					new HashMap<String,PrintWriter>();
			while(true){
				Socket sock = server.accept();
				ChatThread chatthread = new ChatThread(sock, hm);
				chatthread.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void init(){
		txtMain = new JTextField();
	}
	
	private void setDisplay(){
		
	}
	
	private void showFrame(){
		setSize(400,300);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
