package Lec3;

public class CallMaker extends Thread {
	MyPrinter src;
	public CallMaker(MyPrinter src) {
		this.src=src;
	}
	public void run(){ //����Ʈ�ȿ��ִ� endstart ���
		while(true){
			src.maker();
		}
	}
}
