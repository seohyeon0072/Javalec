package Lec1;

public class Consumer extends Thread{
	private MyBox box;
	public Consumer(MyBox c) {
		box=c;
	}
	public void run(){
		int value=0;
		for(int i=0;i<10;i++){ 
			box.get(); //²¨³»°í
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}

}
