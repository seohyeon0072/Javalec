package Lec1;

public class MyBox {
	private int contents;
	private boolean isEmpty=true;
	public synchronized void get(){
		if(!isEmpty){ //put�� �ѹ� ��������� get
			isEmpty=!isEmpty; //false
			System.out.println(Thread.currentThread().getName()+
					": �Һ� "+contents);
		}
	}
	public void put(int value){
		if(isEmpty){ //��������� 
			contents=value;
			System.out.println(Thread.currentThread().getName()+
					": ���� "+contents); 
			isEmpty=!isEmpty; //false
		}
	}
}
