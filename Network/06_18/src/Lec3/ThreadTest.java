package Lec3;

public class ThreadTest {
	public static void main(String[] args) {
		MyPrinter src =new MyPrinter();
		CallPrint print = new CallPrint(src);
		CallMaker maker =new CallMaker(src);
		print.start(); 
		maker.start();
	}
}
