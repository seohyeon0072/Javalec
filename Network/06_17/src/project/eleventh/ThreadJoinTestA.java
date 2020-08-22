package project.eleventh;

public class ThreadJoinTestA {
	public static void main(String[] args) {
		// 스레드 생성
		// 이너클래스
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
		System.out.println("main() 종료");
	}
}
