package kr.ac.green;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Vector;

/*
 * ObjectInputStream, ObjectOutputStream
 * ->필터 (읽고 쓰는 대상을 선정할 수 없다.)
 */
public class IOEx1 {
	
	
	// instance -> File
	public static void save() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
 
		try {
			fos = new FileOutputStream("data.dat");
			oos = new ObjectOutputStream(fos);
			// 2개이상의 객체사용시
			Vector<MyObj> vec = new Vector<MyObj>(); 
		
			vec.add(new MyObj("b", 3));
			vec.add(new MyObj("a", 1));
			vec.add(new MyObj("아", 4));
			vec.add(new MyObj("e", 5));
			vec.add(new MyObj("c", 7));
		
			
			Collections.sort(vec, new MemberComparator());
			System.out.println(vec);
			
			// 1개의 파일당 1개의 객체만 사용
			oos.writeObject(vec);
			oos.flush();
			// 갱신오류, 메모리 누수현상
			oos.reset();

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
				
//				MyObj other = (MyObj)o;
//				System.out.println(other.getName());
//				System.out.println(other.getNumber());
				
				System.out.println(vec);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				// 메소드나 생성자는 동작(연산)이라 동일함
				// 객체마다 다른것은 상태이지 동작이아님
				// readObject는 멤버변수밖에 못읽어서 class파일이 있어야 동작까지 들고올수가 있음
				
				// 언마샬링시 필요한 것(Un Mashashdshad)
				// 1.상태정보를 담고있는 파일(멤버변수) private
				// 2.객체를 찍어낸 클래스 파일(메소드, 생성자) public
				// final : 모든객체에서 동일하기 때문에 static 붙여서 써라
				// 상수 : final 객체마다 다른값을 가져야할때, static final : 객체마다 동일한 값
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

	
	}

	public static void main(String[] args) {
		 save();
		load();
	}
}
