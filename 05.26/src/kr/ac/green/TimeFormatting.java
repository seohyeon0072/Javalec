package kr.ac.green;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatting {

	public static void main(String[] args) {
		File f = new File("c:\\study\\a.txt");
		// 마지막 수정 시간
		long time = f.lastModified();

		// 2020-05-26
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d(a hh:mm:ss)");

		// 생성된 현재 시간을 가지고 태어남
		Date toDay = new Date();
		// 원하는 시간 설정
		toDay.setTime(time);

		// 시간을 문자열로 변경
		String strToday = sdf.format(toDay);

		try {
			// 문자열 -> 시간(Date)
			Date newDate = sdf.parse(strToday);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(strToday);

	}

}