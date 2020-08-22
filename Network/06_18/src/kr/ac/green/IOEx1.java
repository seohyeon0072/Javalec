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
 * ->���� (�а� ���� ����� ������ �� ����.)
 */
public class IOEx1 {
	
	
	// instance -> File
	public static void save() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
 
		try {
			fos = new FileOutputStream("data.dat");
			oos = new ObjectOutputStream(fos);
			// 2���̻��� ��ü����
			Vector<MyObj> vec = new Vector<MyObj>(); 
		
			vec.add(new MyObj("b", 3));
			vec.add(new MyObj("a", 1));
			vec.add(new MyObj("��", 4));
			vec.add(new MyObj("e", 5));
			vec.add(new MyObj("c", 7));
		
			
			Collections.sort(vec, new MemberComparator());
			System.out.println(vec);
			
			// 1���� ���ϴ� 1���� ��ü�� ���
			oos.writeObject(vec);
			oos.flush();
			// ���ſ���, �޸� ��������
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
				// �޼ҵ峪 �����ڴ� ����(����)�̶� ������
				// ��ü���� �ٸ����� �������� �����̾ƴ�
				// readObject�� ��������ۿ� ���о class������ �־�� ���۱��� ���ü��� ����
				
				// �𸶼����� �ʿ��� ��(Un Mashashdshad)
				// 1.���������� ����ִ� ����(�������) private
				// 2.��ü�� �� Ŭ���� ����(�޼ҵ�, ������) public
				// final : ��簴ü���� �����ϱ� ������ static �ٿ��� ���
				// ��� : final ��ü���� �ٸ����� �������Ҷ�, static final : ��ü���� ������ ��
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
