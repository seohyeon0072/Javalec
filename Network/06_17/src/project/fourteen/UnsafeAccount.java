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
		// -나오는 이유
		// 다중스레드 사용시
		if(balance >= val){
			try{
				// sleep이 붙어서 NotRunnable로 빠지기 때문에
				// 다른스레드들이 들어오면 처리하기 전에 잠들엇기 때문에 
				// 값이 처리되기도전에 중복해서 작동됨
				// t1이 withdraw를 다 처리하지 못햇는데 t2도 들어와버림
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
