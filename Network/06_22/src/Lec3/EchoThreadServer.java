package Lec3;

import java.net.ServerSocket;
import java.net.Socket;

public class EchoThreadServer {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(10003);
			System.out.println("������ ��ٸ��ϴ�");
			int count=0;
			while(true){
				Socket sock = server.accept();
				System.out.println(sock);
				EchoThread echothread = new EchoThread(sock);
				echothread.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
