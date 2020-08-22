package Network_Lec2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ObjectCalculatorClient {
	public static void main(String[] args) {
		String ip = JOptionPane.showInputDialog("ip입력하세요");
		Socket sock = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			sock = new Socket(ip, 10005);
			oos = new ObjectOutputStream(sock.getOutputStream());
			ois = new ObjectInputStream(sock.getInputStream());
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			
			String line = null;
			while (true) {
				System.out.println("첫번째 숫자를 입력하여 주세요.(잘못 입력된 숫자는 0으로 처리합니다)");
				line = keyboard.readLine();
				int op1 = 0;
				try {
					op1= Integer.parseInt(line);
				} catch (NumberFormatException e) {
					// TODO: handle exception
					op1=0;
				}
				System.out.println("두번째 숫자를 입력하여 주세요.(잘못 입력된 숫자는 0으로 처리합니다)");
				line = keyboard.readLine();
				int op2 = 0;
				try {
					op2= Integer.parseInt(line);
				} catch (NumberFormatException e) {
					// TODO: handle exception
					op2=0;
				}
				System.out.println("+,-,*,/ 중 하나를 입력하세요(잘못 입력하면  + 로 처리합니다)");
				line = keyboard.readLine();
				String opcode="+";
				if(line.equals("+")||line.equals(" -")||line.equals("*")||line.equals("/")){
					opcode = line;
				}else{
					opcode = "+";
				}
				SendData s = new SendData(op1,op2,opcode);
				oos.writeObject(s);
				oos.flush();
				oos.reset();
				String msg = (String)ois.readObject();
				System.out.println(msg);
				System.out.println("계속 계산하시겠습니까(Y/N)");
				line = keyboard.readLine();
				if(line.equals("n"))break;
				System.out.println("다시 계산을 시작합니다");
					
			}
			System.out.println("프로그램을 종료합니다");
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			try {
				if(oos!= null)oos.close();
			} catch (Exception e2) {
			}
			try {
				if(ois!= null)ois.close();
			} catch (Exception e2) {
			}
			try {
				if(sock!= null)sock.close();
			} catch (Exception e2) {
			}
		}
	}
}
