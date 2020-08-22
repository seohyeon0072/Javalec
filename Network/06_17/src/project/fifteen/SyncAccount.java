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

	// synchronizedŰ���� : ����ȭ(��ȣ������ �����ϴ� �޼ҵ�)
	// �ٸ� �����尡 ��� �� �� ���� ���� ��Ź�����
	// �޼ҵ带 Ż���Ҷ� ������
	// this�� ���� ���� �˻�
	// ��ũ�γ����� �޼ҵ� ����(���ʿ��� ����鵵 ����Ǽ� ��ȿ��)
	public synchronized void withdraw(int val) {

		// ���� ��ü�� ���� ������ �Ҷ�
		if (balance >= val) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {

			}
			balance -= val;
		}
		System.out.println("name : " + Thread.currentThread().getName() + ", balance=" + this.getBalance());
	}

	// Object�� ���˻縸 ��
	// ��ũ�γ����� ������(������ ���������ؼ� ȿ������)
	// public void withdraw(int val) {
	//
	// // ���� ��ü�� ���� ������ �Ҷ�
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
