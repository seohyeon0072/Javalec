package kr.ac.green;

import java.lang.reflect.Member;
import java.util.Comparator;

class MemberComparator implements Comparator<MyObj> {
	public int compare(MyObj m1, MyObj m2) {
//		return m1.getName().compareTo(m2.getName());
		return m1.getNumber() - m2.getNumber();
	}
}
