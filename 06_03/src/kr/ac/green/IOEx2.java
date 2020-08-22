package kr.ac.green;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IOEx2 {
	public static void save() {
		try (			
			ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("data2.dat")
			);
		){
			oos.writeObject(new Foo(100, "some"));
			oos.flush();
			oos.reset();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * InvalidClassException
	 * write 하는 시점의 클래스 파일과 일치하지 않는 경우
	 */
	public static void load() {
		try (
			ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("data2.dat")
			);			
		){
			System.out.println(ois.readObject());	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	public static void main(String[] args) {
		save();
		load();
	}
}
