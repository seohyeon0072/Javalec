package Lec2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket (10003); //��Ʈ ���� Ŭ���̾�Ʈ�� ������ ��ٸ��ڴ�.
			System.out.println("������ ��ٸ��ϴ�");
			Socket sock = server.accept(); // ����ڰ� ������ �Ҷ����� ��� (not runnable) ->�����ϸ� (runnable)
			InetAddress inetaddr = sock.getInetAddress(); 
			System.out.println(
					inetaddr.getHostAddress()+" �κ��� �����Ͽ����ϴ�");
			OutputStream out = sock.getOutputStream(); //����
			InputStream in = sock.getInputStream(); //�б� 
			
			PrintWriter pw = new PrintWriter( //�ٴ����� ���� ����
					new OutputStreamWriter(out)); //ĳ���͸� ����Ʈ�� ���°�
			BufferedReader br = new BufferedReader(
					new InputStreamReader(in));//����Ʈ�� ĳ����
			String line = null; //readLine �����ԵǸ� not runnable ���·� �����Ե�
			while((line = br.readLine())!= null){
				System.out.println("Ŭ���̾�Ʈ�� ���� ���۹��� ���ڿ� :"+line);
				pw.println(line);
				pw.flush();
			}
			pw.close();
			br.close();
			sock.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
