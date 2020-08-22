package project.twelfth;

public class ThreadJoinTestB {
	public static void main(String[] args) {
		// 스레드 생성
		// 람다식 가능
		Thread t = new Thread(){
			public void run(){
				try{
					// 2초간 멈춤
					Thread.sleep(2000);
					// 스레드 종료 메세지
					System.out.println("MyThread 종료");
					// 3초간 멈춤
					Thread.sleep(3000);
				}catch(Exception e){}
			}
		};
		// 스레드 시작
		t.start();
		try{
			// join() 메소드 실행..
			// t 스레드가 종료될때까지 main 스레드가 기다림.
			// t.join()은 스레드 t가 영향을 받는게 아니라 실행한(메인스레드)가 t스레드가 합류할떄까지 기다림
			// 스레드 t가 해야 할 일이 다 끝났을때까지.. join()을 만나면 NOT RUNNABLE상태로 바뀜
			// t스레드가 일을 다 끝내면 실행대기상태로와서 다시 실행됨
			// 
			t.join();
			// InterruptedException : sleep메소드 쓸때
			// 스레드의 상태를 실행불가상태로 정의시키는 메소드
			// NOT RUNNABLE로 변경될때 발생되는 예외
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		// main 메소드 종료 메시지
		System.out.println("main() 종료");
	}
}
