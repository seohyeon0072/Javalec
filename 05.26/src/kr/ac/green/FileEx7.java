package kr.ac.green;

import java.io.File;
import java.io.IOException;

public class FileEx7 {

	public static void main(String[] args) {
		// root 부터 출발한게 아니라서 상대경로로 ...
		File f = new File("..\\..\\a.txt");


		/*
		 * 상대 경로 : (현재 위치가 출발점) .. (상위폴더) , . (현재위치) 절대 경로 : c:\study\a\b\a.txt
		 * (무조건 루트 부터 출발(위치 정보가 절대 변하지 않음))
		 */

		System.out.println(f.getParent());
		System.out.println(f.getName());
		System.out.println(f.getPath());
		System.out.println();
		
		// 첫 번째 방법 ( 절대 경로 ) 문자열로 돌려받음
		// System.out.println(f.getAbsolutePath());

		// 2. 파일 형태의 객체로 돌려 받을 수 있다.
		// 패키지는 포함 안됨
		File other = f.getAbsoluteFile();
		System.out.println(other.getParent());
		System.out.println(other.getName());
		System.out.println(other.getPath());
		System.out.println();
		try {
			// 이산경로
			// System.out.println(f.getCanonicalPath());
			File another = f.getCanonicalFile();
			System.out.println(another.getParent());
			System.out.println(another.getName());
			System.out.println(another.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			File another = f.getCanonicalFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}