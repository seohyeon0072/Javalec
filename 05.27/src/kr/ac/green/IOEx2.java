package kr.ac.green;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOEx2 {

	public static void main(String[] args) {
		InputStream is = System.in;
		
		// inputstream 을 읽어서 char 로 전환
		InputStreamReader isr = null;
		
		BufferedReader br = null;
		
		try {
			isr = new InputStreamReader(is);
			int data = -1;
			String line = null;
//			while((data = isr.read()) != -1) {
//				System.out.println((char)data);
//			}
			while((line = br.readLine()) != null) {
				System.out.println((char)data);
			}
		}catch (IOException e) {
		} finally {
			MyUtils.closeAll(isr, br, is);
			
		}
		
		
	}

}
