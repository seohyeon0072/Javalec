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
		prop.setProperty("Label", "���̺�");
		prop.setProperty("File", "����");

		FileOutputStream fos = null;
		try {
			// ��� ���� �����ϴ� xml �� �� ����
			fos = new FileOutputStream("lang.properties");
			prop.store(fos, "hello");
			//prop.storeToXML(fos, "hi~");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
			}
		}
		Set<?> keys = prop.keySet();
		for(Object key : keys) {
			System.out.println(key.toString());
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
