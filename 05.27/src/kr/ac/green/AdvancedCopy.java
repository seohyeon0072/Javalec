package kr.ac.green;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class AdvancedCopy {

	public static void newCopy(File f) {
		File copy = new File("");
		File src = new File("");

		FileInputStream fis = null;
		FileOutputStream fos = null;

		long time = System.currentTimeMillis();
		try {
			fis = new FileInputStream(src);
			fos = new FileOutputStream(copy);

			int count = -1;
			byte[] buffer = new byte[5000];

			while ((count = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, count);
			}
			
			
		} catch (Exception e) {

		} finally {
			MyUtils.closeAll(fis, fos);
		}
	}

	public static void main(String[] args) {

	}

}
