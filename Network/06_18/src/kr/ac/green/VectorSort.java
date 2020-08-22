package kr.ac.green;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class VectorSort {
	public static void main(String[] args) {
		Vector<MyObj> vec = new Vector<MyObj>();

		Collections.sort(vec, new MemberComparator());

		System.out.println(vec);
		
//		for (int i = 0; i < vec.size(); i++) {
//			MyObj mo = (MyObj) vec.get(i); 
//			System.out.println("이름 = " + mo.getName() + " 나이 = " + mo.getNumber());
//		}

	}

}
