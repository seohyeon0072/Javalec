package project.eleventh;

public class ThreadJoinTestA {
	public static void main(String[] args) {
		// ������ ����
		// �̳�Ŭ����
		Thread t = new Thread(){
			public void run(){
				try{
					// 2�ʰ� ����
					Thread.sleep(2000);
					// ������ ���� �޼���
					System.out.println("MyThread ����");
					// 3�ʰ� ����
					Thread.sleep(3000);
				}catch(Exception e){}
			}
		};
		// ������ ����
		t.start();
		System.out.println("main() ����");
	}
}
