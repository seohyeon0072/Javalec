package Quiz;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class PropertiesEx {
	/*
	 * Map �迭 hashTabel ����� ���Ͽ� ���� �б� ����� ���� key value->String
	 */
	public static void save() {
		Properties prop = new Properties();
		// put
		prop.setProperty("Korean", "�ѱ�");
		prop.setProperty("English", "����");
		
		prop.setProperty("Label", "���̺�");
		prop.setProperty("File", "����");
		
		prop.setProperty("Save", "����");
		prop.setProperty("Open", "����");

		prop.setProperty("�ѱ�", "Korean");
		prop.setProperty("����", "English");
		
		prop.setProperty("���̺�", "Label");
		prop.setProperty("����", "File");
		
		prop.setProperty("����", "Save");
		prop.setProperty("����", "Open");
		
		FileOutputStream fos = null;
		try {
			// ��� ���� �����ϴ� xml �� �� ����
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
