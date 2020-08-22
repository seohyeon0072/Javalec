package kr.ac.green;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/*
 * ObjectInputStream, ObjectOutputStream 
 * 	-> 필터(읽고 쓰는 대상을 선정할 수 없다.)
 */
public class IOEx1 {
	// instance -> File
	public static void save() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("data.dat");
			oos = new ObjectOutputStream(fos);
			// 1 파일 당 1 개의 객체만 쓰자
			Vector<MyObj> vec = new Vector<MyObj>();
			vec.add(new MyObj("1",1));
			vec.add(new MyObj("2",2));
			vec.add(new MyObj("3",3));
			vec.add(new MyObj("34",3));
			oos.writeObject(vec);
			oos.flush();
			oos.reset();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			MyUtils.closeAll(oos, fos);
		}
	}

	public static void load() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream("data.dat");
			ois = new ObjectInputStream(fis);
			
			Object o = ois.readObject();
			Vector<?> vec = (Vector<?>)o;
			System.out.println(o);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			MyUtils.closeAll(ois, fis);
		}

	}

	public static void main(String[] args) {
		save();
	//	load();
	}
}
