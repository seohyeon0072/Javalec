package project.fourteen;

public class UnsafeAccount {
	private int balance;
	public int getBalance(){
		return balance;
	}
	public void deposit(int val){
		balance += val;
	}
	public void withdraw(int val){
		// -������ ����
		// ���߽����� ����
		if(balance >= val){
			try{
				// sleep�� �پ NotRunnable�� ������ ������
				// �ٸ���������� ������ ó���ϱ� ���� ������ ������ 
				// ���� ó���Ǳ⵵���� �ߺ��ؼ� �۵���
				// t1�� withdraw�� �� ó������ ���޴µ� t2�� ���͹���
				Thread.sleep(500); //Not Runnable
			}catch(InterruptedException e){ 
				
			}
			balance -= val;
		}
		System.out.println(
				"name : " + Thread.currentThread().getName()+
				", + balance=" + this.getBalance());
	}
}
