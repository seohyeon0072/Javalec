package kr.ac.green;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOEx1 {

	//파일에 바이트 단위로 쓰기
	public static void utilJDK6() {
		// ~ JDK 1.6

		// 외부 자원에 접근하는 놈
		FileOutputStream fos = null;

		try {
			//appendable
//			fos = new FileOutputStream("c:\\study\\a.txt", true);
			fos = new FileOutputStream("c:\\study\\a.txt");
			// 실제로 들어오는 값은 byte ( byte 로 쓰게되면... )
			// byte < char < int
			try {
				fos.write('A');
				fos.write('B');
				fos.write('C');
				fos.write('D');
				fos.write('E');
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				// if(fos != null) {
				// fos.close();
				// }
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//파일 읽기
	public static void utilJDK7() {
		// AutoCloseable 구현한 애들만 올 수 있다.
		try (FileInputStream fis = new FileInputStream("c:\\study\\a.txt")) {
			int data = -1;
			
			while((data = fis.read()) != -1) {
				System.out.println((char)data);
			}
			
//			boolean flag = true;
//			while(flag) {
//				data = fos.read();
//				//read() : EOF(End Of File)를 만나면 -1리턴
//				if(data == -1) {
//					flag = false;
//				} else {
//					System.out.println((char)data);
//				}
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	// 파일에 쓰기(byte)
	public static void main(String[] args) {
		utilJDK6();
		utilJDK7();
	}

}
