package Lec4;
class Dummy{
	public synchronized void todo(){
		try {
			System.out.println("start..");
			wait();  //락을 가진 스레드만  no runnable
//			Thread.sleep(2000);
		} catch (InterruptedException e) {//실행 대기열로 돌아온다
			 //상태를 옮기기위해 예외를 발생 시킨다?
			System.out.println("interrupted!!");
		}
	}
}
public class InterruptEx {
	public static void main(String[] args) {
		final Dummy d = new Dummy(); 
		Thread t1= new Thread(){//이너클래스
			@Override
			public void run(){
				d.todo();
				System.out.println("i'm dead ");
			}
		};
		t1.start();
		t1.interrupt(); //예외가 발생함으로써 실행 가능한 상태로 돌아감 -실행대기열
	}
}
