package kr.ac.green;

import java.io.Closeable;

public class MyUtils {

	public static void closeAll(Closeable... c) {
		for (Closeable temp : c) {
			try {
				temp.close();
			} catch (Exception e) {
			}
		}

	}

}
