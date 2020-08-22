package kr.ac.green;
/*
 * byte : 일반적인 파일 
 * char : 읽고 쓰는 데이터가 순수한 텍스트 이고, 영어가 아닌 문자인 경우
 */

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharIO {

	public static void writeToFile() {
		FileWriter fw = null;

		try {
			fw = new FileWriter("data.txt");
			fw.write("하이~");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			MyUtils.closeAll(fw);
		}
	}
	
	public static void readFromFile() {
		FileReader fr = null;
		
		try {
			fr = new FileReader("data.txt");
			int data = -1; //char
			
			while((data = fr.read()) != -1 ) {
				System.out.println((char)data);
			}
		} catch (IOException e) {
		} finally {
			MyUtils.closeAll(fr);
		}
	}

	public static void main(String[] args) {
		writeToFile();
		readFromFile();
	}
}
