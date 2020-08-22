package kr.ac.green;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileEx6 {

	public static void main(String[] args) {
		File dir = new File("c:");

		File[] list = dir.listFiles();
		// 디렉토리 일 때만 된다~
		// 디렉토리에 존재한 파일을 배열로 돌려준다.

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd(a hh:MM:ss)");
		Date date = new Date();
		for (File temp : list) {
			String name = temp.getName();
			if (temp.isDirectory()) {
				name = "[ " + name + " ]";
			} else {
				long size = temp.length();

				date.setTime(temp.lastModified());

				name = name + "\t" + (size / 1024) + "kb\t" + sdf.format(date);
			}
			System.out.println(name);
		}
	}
}
