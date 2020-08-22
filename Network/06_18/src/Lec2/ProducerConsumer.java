package Lec2;

public class ProducerConsumer {
	public static void main(String[] args) {
		MyBox c= new MyBox();
		Producer p1 = new Producer(c);
		Consumer c1 = new Consumer(c);
		Consumer c2 = new Consumer(c);
		p1.start();
		c1.start();
		c2.start();
	}
}
