package kr.ac.green;

import java.io.File;

public class FileEx3 {

	public static void main(String[] args) {
		File f = new File("c:\\a\\b\\c\\d");
		
		File dir = new File("c:\\a");
		//여러 디렉토리를 나타낼 때 만 적용~
		//파일 경로 한번에 생성
		f.mkdirs();
		
		// 하위 경로가 있으면 지워지지 않는다.
		dir.delete();
	}

}