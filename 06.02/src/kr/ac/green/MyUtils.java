package kr.ac.green;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MyUtils {
	// Properties -> File
	public static void saveProp(Properties prop, String path, String commnet)
			throws NullPointerException, BadFileException, IOException {
		
		if (prop == null) {
			throw new NullPointerException("Properties 객체는 null이 될 수 없습니다");
		}

		nullCheck(path);
		checkFileExt(path);

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path);
			if (isXML(path)) {
				prop.storeToXML(fos, commnet);
			} else {
				prop.store(fos, commnet);
			}
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
			}
		}
	}

	private static void checkFileExt(String path) throws BadFileException {
		int idx = path.lastIndexOf(".");
		if (idx < 0) {
			throw new BadFileException();
		}
	}

	private static boolean isXML(String path) {
		boolean isXML = false;

		int idx = path.lastIndexOf(".");
		String ext = path.substring(idx).toLowerCase();
		if (ext.equals(".xml")) {
			isXML = true;
		}
		return isXML;
	}

	private static void nullCheck(String path) throws NullPointerException {
		if (path == null) {
			throw new NullPointerException("파일 경로는 null이 될 수 없습니다.");
		}

	}
	
	//File -> Properties
	public static Properties loadProp(String path) throws NullPointerException, IOException {
		nullCheck(path);
		checkFileExt(path);
		
		Properties prop = null;
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(path);
			prop = new Properties();
			if(isXML(path)) {
				prop.loadFromXML(fis);
			} else {
				prop.load(fis);
			}
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
		return prop;
	}
}
