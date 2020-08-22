package Lec3;

public class MyPrinter {
	private boolean state = true;

	public synchronized void printContents() {
		while (state) { //true
			try {
				System.out.println("문자 기다린다 ");
				wait();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (char c = 'a'; c <= 'z'; c++) {
			System.out.print(c);
		}
		System.out.println();
		state = !state; //state == false
		notifyAll(); //락 풀어줌
	}

	public synchronized void maker() {
		while (!state) { //false
			try {
				System.out.println("문자 기다린다 ");
				wait();
				System.out.println("end printing");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("start printing");
		state = !state;
		notifyAll();

	}
}
