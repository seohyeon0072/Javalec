package Lec3;

public class CallMaker extends Thread {
	MyPrinter src;
	public CallMaker(MyPrinter src) {
		this.src=src;
	}
	public void run(){ //프린트안에있는 endstart 출력
		while(true){
			src.maker();
		}
	}
}
