package Lec3;

public class CallPrint extends Thread{
	MyPrinter src;
	public CallPrint(MyPrinter src) {
		this.src=src; 
	}
	public void run(){ //printer안에있는 문자 출력
		while(true){ 
			src.printContents();
		}
	}
}
