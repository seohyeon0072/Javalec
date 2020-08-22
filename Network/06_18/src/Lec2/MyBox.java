package Lec2;

public class MyBox {
	private int contents;
	private boolean isEmpty=true;
	
	//lock���� ȣ�Ⱑ�� wait�� notifyAll
	
	//�Һ�
	public synchronized int get(){
		while(isEmpty){
			try {
			//	System.out.println(Thread.currentThread().getName()+"wait");
				wait(); // 	���� �ִ� ���� ���� �����ϰ�, �����带 ���� �Ѵ� run->not runnable(lock Ǯ��) 
						//	lock�� ����� �޼ҵ� synchronized ���̳� �޼ҵ� ���ο����� wait, modifyAll���� 
						// 	�ð����� ��ٸ��°� �ƴϰ� signal�� �ö����� ��ٸ� 
			
			} catch (InterruptedException e) {
			}
		}
		isEmpty=!isEmpty; 
		notifyAll(); //�������� waiting���¿� ���� thread���� ��� ����� �޼ҵ� (notify�� �ϳ��� ����)
		//  not runnable -> runnable ������ lock�� ������ �ִٰ� wait �� ��� �����带 ���� // �������� ����������
		System.out.println(Thread.currentThread().getName()+
				": �Һ� "+contents); 
		return contents;
		
	}
	//����
	public synchronized void put(int value){
		while(!isEmpty){ //false�� �ɶ�����
			try {
			//	System.out.println(Thread.currentThread().getName()+"wait");
				wait();
			} catch (InterruptedException e) {
			}
		}
		contents = value; 
		System.out.println(Thread.currentThread().getName()+
				": ���� "+contents); 
		isEmpty=!isEmpty; 
		
		notifyAll();
	}
}
