package kr.ac.green;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class IOEx3 {

	public static void consoleToFile(String fileName) {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		FileWriter fw = null;
		
		// 줄 단위로 쓰는거
		PrintWriter pw = null;
		
		try {
			//표준 입력
			is = System.in;
			
			//
			isr = new InputStreamReader(is);
			
			
			br = new BufferedReader(isr);
			
			fw = new FileWriter(fileName);
			
			pw = new PrintWriter(fw);
			
			String line = null;
			
			while((line = br.readLine()) != null) {
				//fw.write(line);
				pw.printf("%s\n", line);
//				pw.write(line + "\r\n");
				
			}
			
			//내용을 비운다 .. (출력을 보장)
			pw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 자원 해제 : 생성된 역순으로 닫는다.
			MyUtils.closeAll(br, isr, is);
		}
	}
	
	public static void main(String[] args) {
		consoleToFile("console.txt");
	}
}