package Lec3;

public class CallPrint extends Thread{
	MyPrinter src;
	public CallPrint(MyPrinter src) {
		this.src=src; 
	}
	public void run(){ //printer�ȿ��ִ� ���� ���
		while(true){ 
			src.printContents();
		}
	}
}
