package project.thirteenth;

public class ThreadEx13 {
	static long startTime = 0;

	public static void main(String[] args) {
		ThreadEx13_1 th1 = new ThreadEx13_1();

		ThreadEx13_2 th2 = new ThreadEx13_2();

		th1.start();
		th2.start();

		startTime = System.currentTimeMillis();
	/*	try{
		// 메인쓰레드 멈춤, 스레드 1번의 동작이 끝날떄까지 기다림
		// 경우의 수 2가지
		// 쓰레드2번이 먼저 끝났느냐 안 끝났느냐
		// 만약 2번이 끝났으면 기다릴 대상이 없기때문에 바로 종료
		// 만약 2번이 아직까지 일을 하고 있는 상황이면 2번을 기다렸다가 종료
		 th1.join();
		 th2.join();
		 }catch(InterruptedException e){}
 */
		// 정석
		try {
			th1.join();
			// 스레드 상태를 NotRunnable상태로 바꾸는게 있을시 InterruptedException예외가 따라옴
			// 입출력연산빼고는 없음
		} catch (InterruptedException e) {
		}
		
		try {
			th2.join();
		} catch (InterruptedException e) {
		}

		System.out.print("소요시간:" + (System.currentTimeMillis() - ThreadEx13.startTime));
	}// main
}
