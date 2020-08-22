package Lec1;

public class MyBox {
	private int contents;
	private boolean isEmpty=true;
	public synchronized void get(){
		if(!isEmpty){ //put이 한번 실행됫을때 get
			isEmpty=!isEmpty; //false
			System.out.println(Thread.currentThread().getName()+
					": 소비 "+contents);
		}
	}
	public void put(int value){
		if(isEmpty){ //비엇을때만 
			contents=value;
			System.out.println(Thread.currentThread().getName()+
					": 생산 "+contents); 
			isEmpty=!isEmpty; //false
		}
	}
}
