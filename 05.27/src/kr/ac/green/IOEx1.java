package kr.ac.green;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOEx1 {
	public static void main(String[] args) {
		/*
		 * 키보드 입력을 읽어 보자.
		 */

		InputStream is = System.in;
		// enter = \r + \n
		int data = -1;
		try {
			while ((data = is.read()) != -1) {
				System.out.println("읽은 내용 : " + (char)data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("EOM");
		// OutputStream os = System.out;
	}
}
