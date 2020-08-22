package kr.ac.green;

import java.io.Serializable;
import java.util.Comparator;

// 직렬화를 지원하는 객체, Serializable구현해야함
// 직렬화를 지원하는 객체는 모든 멤버변수가 직렬화를 지원해야한다
// 하나라도 빠지면 직렬화가 되지 않는다.
// 이상이 없으면 writeObject가 됨
public class MyObj implements Serializable {
	private String name;
	private int number;
	
	public MyObj(String name, int number) {
		super();
		this.name = name;
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		String info = "name : " + name + "\n";
		info+= "number : " + number;
		return info;
	}
}
