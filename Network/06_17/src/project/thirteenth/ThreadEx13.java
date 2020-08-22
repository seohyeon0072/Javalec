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
		// ���ξ����� ����, ������ 1���� ������ ���������� ��ٸ�
		// ����� �� 2����
		// ������2���� ���� �������� �� ��������
		// ���� 2���� �������� ��ٸ� ����� ���⶧���� �ٷ� ����
		// ���� 2���� �������� ���� �ϰ� �ִ� ��Ȳ�̸� 2���� ��ٷȴٰ� ����
		 th1.join();
		 th2.join();
		 }catch(InterruptedException e){}
 */
		// ����
		try {
			th1.join();
			// ������ ���¸� NotRunnable���·� �ٲٴ°� ������ InterruptedException���ܰ� �����
			// ����¿��껩��� ����
		} catch (InterruptedException e) {
		}
		
		try {
			th2.join();
		} catch (InterruptedException e) {
		}

		System.out.print("�ҿ�ð�:" + (System.currentTimeMillis() - ThreadEx13.startTime));
	}// main
}
