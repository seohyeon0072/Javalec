package SourceList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MyUtils {
	public static void saveProp(Properties prop, String path, String comment)
			throws NullPointerException, BadFileException, IOException {
		if (prop == null) {
			throw new NullPointerException("Properties ��ü��  null�� �� �� �����ϴ�");
		}
		nullCheck(path);
		checkFileExt(path);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path);
			if (isXML(path)) {
				prop.storeToXML(fos, comment);
			} else {
				prop.store(fos, comment);
			}
		} catch (Exception e) {
			// TODO: handle exception
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
			throw new NullPointerException("���� ��δ� null�� �� �� �����ϴ�");

		}
	}
	private static Properties loadProp(String path) throws NullPointerException , IOException{
		nullCheck(path);
		checkFileExt(path);
		Properties prop=null;
		FileInputStream fis=null; 
		
		try {
			fis = new FileInputStream(path);
			prop = new Properties();
			if(isXML(path)){
				prop.loadFromXML(fis);
			}else{
				prop.load(fis);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				fis.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return prop;
	}
	 
}