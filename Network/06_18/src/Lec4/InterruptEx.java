package Lec4;
class Dummy{
	public synchronized void todo(){
		try {
			System.out.println("start..");
			wait();  //���� ���� �����常  no runnable
//			Thread.sleep(2000);
		} catch (InterruptedException e) {//���� ��⿭�� ���ƿ´�
			 //���¸� �ű������ ���ܸ� �߻� ��Ų��?
			System.out.println("interrupted!!");
		}
	}
}
public class InterruptEx {
	public static void main(String[] args) {
		final Dummy d = new Dummy(); 
		Thread t1= new Thread(){//�̳�Ŭ����
			@Override
			public void run(){
				d.todo();
				System.out.println("i'm dead ");
			}
		};
		t1.start();
		t1.interrupt(); //���ܰ� �߻������ν� ���� ������ ���·� ���ư� -�����⿭
	}
}
