package Lec3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
	public static void main(String[] args) {
		try {
			//IP,PORT ������ Ŭ���̾�Ʈ�� ����Ǿ��� 
			Socket sock = new Socket("192.168.70.93",10001); 
			BufferedReader keyboard =
					new BufferedReader(new InputStreamReader(System.in));
			
			OutputStream out = sock.getOutputStream();
			InputStream in = sock.getInputStream();
			//�ٴ���
			PrintWriter pw = new PrintWriter(
					new OutputStreamWriter(out));
			BufferedReader br = new BufferedReader( //�����κ��� ���� �� �ִ������� �ٴ���
					new InputStreamReader(in));
			String line = null;
			
			while((line = keyboard.readLine())!=null){ //������ ���߰� Ŭ���̾�Ʈ�� not Runnable  
				if(line.equals("quit")) break; 
				pw.println(line);
				pw.flush();
				String echo=br.readLine();
				System.out.println("�����κ��� ���� ���� ���ڿ�:"+echo);
		
			}
			pw.close();
			br.close();
			sock.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}
