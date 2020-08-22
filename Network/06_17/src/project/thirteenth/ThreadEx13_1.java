package project.thirteenth;

public class ThreadEx13_1 extends Thread{
	public void run(){
		for(int i = 0; i < 300; i++){
			System.out.print("1");
		}
	}// run()
}
