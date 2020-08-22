package project.fourteen;

public class UnsafeAccountSample {
	public static void main(String[] args) {
		// �̳�Ŭ���������� final����(jdk1.8~����) �Ⱥ��̸� error
		// �д°��� �Ǵµ�
		// ����ȣȯ�κ��� ���ؼ��� final���̴°��� ����
		final UnsafeAccount account = new UnsafeAccount();
		account.deposit(5000);
		Runnable withdrawRun = new Runnable(){
			public void run(){
				for(int i = 0; i < 10; i++){
					account.withdraw(500);
				}
			}
		};
		// 2���̻��� �����忡 ���ؼ� account��ü�� �������̴�
		// 2���� �����尡 ������ ��ü�� �˰������� 
		// 2���� �����尡 �˰� �ִ� account��ü�� ���� �ٸ���.
		// �׷��� �������� ����
		Thread t1 = new Thread(withdrawRun);
		Thread t2 = new Thread(withdrawRun);
		
		t1.start(); 
		t2.start();
	}
}
