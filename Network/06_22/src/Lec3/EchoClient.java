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
			//IP,PORT 서버와 클라이언트가 연결되었다 
			Socket sock = new Socket("192.168.70.93",10001); 
			BufferedReader keyboard =
					new BufferedReader(new InputStreamReader(System.in));
			
			OutputStream out = sock.getOutputStream();
			InputStream in = sock.getInputStream();
			//줄단위
			PrintWriter pw = new PrintWriter(
					new OutputStreamWriter(out));
			BufferedReader br = new BufferedReader( //서버로부터 받을 수 있는정보를 줄단위
					new InputStreamReader(in));
			String line = null;
			
			while((line = keyboard.readLine())!=null){ //서버도 멈추고 클라이언트도 not Runnable  
				if(line.equals("quit")) break; 
				pw.println(line);
				pw.flush();
				String echo=br.readLine();
				System.out.println("서버로부터 전달 받은 문자열:"+echo);
		
			}
			pw.close();
			br.close();
			sock.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}
