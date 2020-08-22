package kr.ac.green;

import java.io.Serializable;
// 직렬화 지원
public class MyObj implements Serializable {
	private String name;
	private int number;
	private Some s = new Some();
	
	public MyObj() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyObj(String name, int number) {
		super();
		this.name = name;
		this.number = number;
	}
	@Override
	public String toString() {
		return "MyObj [name=" + name + ", number=" + number+"]";
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
	public Some getS() {
		return s;
	}
	public void setS(Some s) {
		this.s = s;
	}
	
	
}
