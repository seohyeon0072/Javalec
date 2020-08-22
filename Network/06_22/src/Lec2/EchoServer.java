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
			ServerSocket server = new ServerSocket (10003); //포트 지정 클리이언트의 접속을 기다리겠다.
			System.out.println("접속을 기다립니다");
			Socket sock = server.accept(); // 사용자가 접속을 할때까지 대기 (not runnable) ->접속하면 (runnable)
			InetAddress inetaddr = sock.getInetAddress(); 
			System.out.println(
					inetaddr.getHostAddress()+" 로부터 접속하였습니다");
			OutputStream out = sock.getOutputStream(); //쓰기
			InputStream in = sock.getInputStream(); //읽기 
			
			PrintWriter pw = new PrintWriter( //줄단위로 쓰는 역할
					new OutputStreamWriter(out)); //캐릭터를 바이트로 쓰는거
			BufferedReader br = new BufferedReader(
					new InputStreamReader(in));//바이트를 캐릭터
			String line = null; //readLine 만나게되면 not runnable 상태로 빠지게됨
			while((line = br.readLine())!= null){
				System.out.println("클라이언트로 부터 전송받은 문자열 :"+line);
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
