package project.fourteen;

public class UnsafeAccountSample {
	public static void main(String[] args) {
		// 이너클래스때문에 final붙음(jdk1.8~이전) 안붙이면 error
		// 읽는것은 되는데
		// 버전호환부분을 위해서도 final붙이는것이 좋음
		final UnsafeAccount account = new UnsafeAccount();
		account.deposit(5000);
		Runnable withdrawRun = new Runnable(){
			public void run(){
				for(int i = 0; i < 10; i++){
					account.withdraw(500);
				}
			}
		};
		// 2개이상의 스레드에 의해서 account객체가 공유중이다
		// 2개의 스레드가 동일한 객체를 알고있지만 
		// 2개의 스레드가 알고 있는 account객체가 서로 다르다.
		// 그래서 문제점이 있음
		Thread t1 = new Thread(withdrawRun);
		Thread t2 = new Thread(withdrawRun);
		
		t1.start(); 
		t2.start();
	}
}
