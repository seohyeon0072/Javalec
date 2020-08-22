package kr.ac.green;

import java.io.File;
import java.io.IOException;

public class FileEx2 {

	public static void main(String[] args) {
		// exists : 디스크 상의 존재 여부 확인
		// true : 물리적 파일
		// false : 논리적 파일

		File dir = new File("c:\\study");
		if (!dir.exists()) {
			// 디렉터리가 존재하지 않으면 경로를 생성
			dir.mkdir();
		}

		// 논리적 파일
		File f = new File(dir, "a.txt");

		if (f.exists()) {
			f.delete();
			dir.delete();

		} else {
			try {
				// 물리적 파일 생성
				// 파일을 만들기 위해서 디렉터리 경로가 존재하는지 확인
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		// 파일 존재 유무 확인 return boolean
		// true 나오면 물리적인 파일 , false 는 논리적파일
		// System.out.println(f.exists());

		// 논리적 파일 -> 물리적 파일로 생성

	}
}