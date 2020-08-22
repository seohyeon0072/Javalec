package kr.ac.green;

import java.io.Serializable;

public class Foo implements Serializable {
	/**
	 * 
	 */
	// �ܺο��� ������ ������� �ƴ� static final, Ÿ�� long
	private static final long serialVersionUID = 1L;
	
	// ����ȭ ��󿡼� ����
	transient private int num;
	private String str;
//	private double pi;


	public Foo(int num, String str) {
		super();
		this.num = num;
		this.str = str;
	}
//	public double getPi() {
//		return pi;
//	}
//	
//	public void setPi(double pi) {
//		this.pi = pi;
//	}

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
