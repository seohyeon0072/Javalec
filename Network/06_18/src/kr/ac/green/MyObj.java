package kr.ac.green;

import java.io.Serializable;
import java.util.Comparator;

// ����ȭ�� �����ϴ� ��ü, Serializable�����ؾ���
// ����ȭ�� �����ϴ� ��ü�� ��� ��������� ����ȭ�� �����ؾ��Ѵ�
// �ϳ��� ������ ����ȭ�� ���� �ʴ´�.
// �̻��� ������ writeObject�� ��
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
