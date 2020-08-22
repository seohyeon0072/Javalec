package kr.ac.green;

import java.io.File;
import java.io.IOException;

public class FileEx5 {
	public static void main(String[] args) {
		File f = new File("c:\\study\\a.txt");
		try {
			f.createNewFile();

			
			System.out.println(f.getParent());

			System.out.println(f.getName());

			System.out.println(f.getPath());

			// 크기(Byte) 한글 2바이트 영어 1바이트
			System.out.println(f.length());

			// 마지막 수정 시간
			long n = f.lastModified();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}