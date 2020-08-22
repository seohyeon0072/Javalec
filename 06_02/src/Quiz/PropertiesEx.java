package Quiz;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class PropertiesEx {
	/*
	 * Map 계열 hashTabel ㅅ상속 파일에 쓰기 읽기 기능이 있음 key value->String
	 */
	public static void save() {
		Properties prop = new Properties();
		// put
		prop.setProperty("Korean", "한글");
		prop.setProperty("English", "영어");
		
		prop.setProperty("Label", "레이블");
		prop.setProperty("File", "파일");
		
		prop.setProperty("Save", "저장");
		prop.setProperty("Open", "열기");

		prop.setProperty("한글", "Korean");
		prop.setProperty("영어", "English");
		
		prop.setProperty("레이블", "Label");
		prop.setProperty("파일", "File");
		
		prop.setProperty("저장", "Save");
		prop.setProperty("열기", "Open");
		
		FileOutputStream fos = null;
		try {
			// 모든 언어에서 접근하는 xml 이 다 같다
			fos = new FileOutputStream("lang.properties");
			prop.store(fos, "hello");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
			}
		}
		
	}
 
	public static void load() {
		FileInputStream fis = null;
		Properties prop = new Properties();

		try {
			fis = new FileInputStream("lang.properties");
			prop.load(fis);
			  
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	 
	public static void main(String[] args) {
		save() ;
		load() ;
		 
	}
}
