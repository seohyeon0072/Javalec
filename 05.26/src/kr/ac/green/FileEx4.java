package kr.ac.green;

import java.io.File;

public class FileEx4 {
	
	// 경로를 나타내는지 파일을 나타내는지 알아야 ....
	public static void whatIsThis(File f) {
		// 물리적 파일 일 때만 올바른 결과를 돌려준다.
		if(!f.isFile()) {
			System.out.println("파일이 아님");
		}
		
		if(!f.isDirectory()) {
			System.out.println("디렉토리가 아님");
		}
	}
	
	public static void main(String[] args) {
		File dir = new File("c:\\study");
		dir.mkdir();
		whatIsThis(dir);
	}

}
