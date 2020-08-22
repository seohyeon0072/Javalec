package project.fifteen;

public class SyncAccount {
	private static Object obj = new Object();
	private int balance;

	// synchronized
	public synchronized int getBalance() {
		return balance;
	}

	public synchronized void deposit(int val) {
		balance += val;
	}

	// synchronized키워드 : 동기화(상호배제를 지원하는 메소드)
	// 다른 스레드가 들어 올 수 없게 문을 잠궈버리고
	// 메소드를 탈출할때 열어줌
	// this에 대한 락을 검사
	// 싱크로나이즈 메소드 형태(불필요한 연산들도 진행되서 비효율)
	public synchronized void withdraw(int val) {

		// 락의 주체를 내가 지정을 할때
		if (balance >= val) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {

			}
			balance -= val;
		}
		System.out.println("name : " + Thread.currentThread().getName() + ", balance=" + this.getBalance());
	}

	// Object의 락검사만 함
	// 싱크로나이즈 블럭형태(범위를 지정가능해서 효율적임)
	// public void withdraw(int val) {
	//
	// // 락의 주체를 내가 지정을 할때
	// synchronized (this) {
	// if (balance >= val) {
	// try {
	// Thread.sleep(500); //Not Runnable
	// } catch (InterruptedException e) {
	//
	// }
	// balance -= val;
	// }
	// System.out.println("name : " + Thread.currentThread().getName() + ",
	// balance=" + this.getBalance());
	// }
	// }
}
