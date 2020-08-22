package Lec4;

public class InterruptEx1 {
	public static void main(String[] args) {
		Thread t1 = new Thread() {

			@Override
			public void run() {
			 //
				try {
					System.out.println("isInterrupted: " + isInterrupted());
					System.out.println("sleep");
					// 1분간 잠든다 RUN->NOT RUNNABLE
					Thread.sleep(1000);
				} catch (InterruptedException e) { // 상태전이//not runnable 상태에서
					// interruptedexception 버ㅏㄷ앗다 는정보 없애버림 
					System.out.println("wake up");
					System.out.println("isInterrupted: " + isInterrupted()); //false 
				}
				for (int i = 0; i < 10; i++) {
					System.out.println(i);
				}
			}
		};
		t1.start();
		
		try {
			Thread.sleep(1200);
		} catch (Exception e) {
		}
		// t1.state : NOT RUNNABLE->RUNNABLE
		t1.interrupt();
	}
}
