package Lec4;

public class InterruptEx2 {
	public static void main(String[] args) {
		Thread t1 = new Thread() {

			@Override
			public void run() {

				long count = 0;
				System.out.println("isInterrupted: " + isInterrupted());
				// t1 start�ǰ��� interrupted���� �ʾҴ�
				while (!isInterrupted()) {// false->true�� �ɶ�����
					// ���� �Ѵ�.
					count++;
				}
				System.out.println("interrupted->count =" + count);
				System.out.println("isInterrupted: " + this.isInterrupted()); 
				System.out.println("Interrupted: " + Thread.interrupted()); //�ʱ�ȭ
				System.out.println("isInterrupted: " + isInterrupted());//false
			}
		};
		t1.start();
		try {
			Thread.sleep(1000);//main
		} catch (Exception e) {
		}
		t1.interrupt();

		// Thread.interrupted();

	}
}
