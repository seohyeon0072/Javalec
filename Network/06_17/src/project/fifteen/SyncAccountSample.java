package project.fifteen;

public class SyncAccountSample {
	public static void main(String[] args) {
		final SyncAccount account = new SyncAccount();
		account.deposit(5000);
		
		//thread »ý¼º 
		Runnable withdrawRun = new Runnable(){
			public void run(){
				for(int i = 0; i < 10; i++){
					account.withdraw(500);
				}
			}
		};
		
		Thread t1 = new Thread(withdrawRun);  
		Thread t2 = new Thread(withdrawRun);
		
		t1.start(); //Runnable 
		t2.start();
	}
}
