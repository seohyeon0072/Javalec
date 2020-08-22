package kr.ac.green;

import java.io.File;

/*
 * java.io.File
 * 		============================================
 * 		형태
 * 
 * 		(2개를 통 틀어서 파일이라고 함)
 * 		1. 파일
 * 		2. 디렉토리(폴더 , 경로) -> 식별자
 * 		============================================
 * 		디스크 상의 존재 여부
 * 
 * 		1. 논리적 파일
 * 			- 코드 상에만 존재하는 정보
 * 		2. 물리적 파일
 * 			- 실제 디스크상에 존재하는 것들
 */
public class FileEx {

	public static void main(String[] args) {
		// target ( c:\study\a.txt )

		// f1, f2, f3 는 같은 파일을 나타냄
		File f1 = new File("c:\\study\\a.txt");
		File f2 = new File("c:\\study", "a.txt");
		// File 은 경로를 나타낼 수 있다.
				File dir = new File("c:\\study");
		File f3 = new File(dir, "a.txt");
		

		
	}
}