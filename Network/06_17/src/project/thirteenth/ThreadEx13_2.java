package project.thirteenth;

public class ThreadEx13_2 extends Thread{
	public void run(){
		for(int i = 0; i < 300; i++){
			System.out.print("2");
		}
	}// run()
}
