package project.twelfth;

public class ThreadJoinTestB {
	public static void main(String[] args) {
		// ������ ����
		// ���ٽ� ����
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
		try{
			// join() �޼ҵ� ����..
			// t �����尡 ����ɶ����� main �����尡 ��ٸ�.
			// t.join()�� ������ t�� ������ �޴°� �ƴ϶� ������(���ν�����)�� t�����尡 �շ��ҋ����� ��ٸ�
			// ������ t�� �ؾ� �� ���� �� ������������.. join()�� ������ NOT RUNNABLE���·� �ٲ�
			// t�����尡 ���� �� ������ ��������·οͼ� �ٽ� �����
			// 
			t.join();
			// InterruptedException : sleep�޼ҵ� ����
			// �������� ���¸� ����Ұ����·� ���ǽ�Ű�� �޼ҵ�
			// NOT RUNNABLE�� ����ɶ� �߻��Ǵ� ����
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		// main �޼ҵ� ���� �޽���
		System.out.println("main() ����");
	}
}
