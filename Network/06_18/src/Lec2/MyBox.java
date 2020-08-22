package Lec2;

public class MyBox {
	private int contents;
	private boolean isEmpty=true;
	
	//lock취득시 호출가능 wait과 notifyAll
	
	//소비
	public synchronized int get(){
		while(isEmpty){
			try {
			//	System.out.println(Thread.currentThread().getName()+"wait");
				wait(); // 	갖고 있던 고유 락을 해제하고, 스레드를 잠들게 한다 run->not runnable(lock 풀고) 
						//	lock을 취득한 메소드 synchronized 블럭이나 메소드 내부에서만 wait, modifyAll가능 
						// 	시간까지 기다리는거 아니고 signal이 올때까지 기다림 
			
			} catch (InterruptedException e) {
			}
		}
		isEmpty=!isEmpty; 
		notifyAll(); //실행중인 waiting상태에 빠진 thread들을 모드 깨우는 메소드 (notify는 하나만 깨움)
		//  not runnable -> runnable 동일한 lock을 가지고 있다가 wait 된 모든 스레드를 깨움 // 스레드의 순차를조절
		System.out.println(Thread.currentThread().getName()+
				": 소비 "+contents); 
		return contents;
		
	}
	//생산
	public synchronized void put(int value){
		while(!isEmpty){ //false가 될때까지
			try {
			//	System.out.println(Thread.currentThread().getName()+"wait");
				wait();
			} catch (InterruptedException e) {
			}
		}
		contents = value; 
		System.out.println(Thread.currentThread().getName()+
				": 생산 "+contents); 
		isEmpty=!isEmpty; 
		
		notifyAll();
	}
}
