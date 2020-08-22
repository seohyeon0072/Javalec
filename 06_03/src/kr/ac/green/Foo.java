package kr.ac.green;

import java.io.Serializable;

public class Foo implements Serializable {
	
	private static final long serialVersionUID = 1;
	
	// 직렬화 대상에서 제외 -> num의 값을 사용하지 않는다
	private int num;
	
	private String str;
	
	public Foo(int num, String str) {
		super();
		this.num = num;
		this.str = str;
	}
	public Foo() {
		super();
	}	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	@Override
	public String toString() {
		return "Foo [num=" + num + ", str=" + str + "]";
	}
	
	
}
