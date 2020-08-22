package Network_UIChat;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class ChatServer extends JFrame {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(10001);
			System.out.println("접속기다린다");

			HashMap<String, PrintWriter> hm = new HashMap<String, PrintWriter>();
			while (true) {
				Socket sock = server.accept();
				ChatThread chatthread = new ChatThread(sock, hm);
				chatthread.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
