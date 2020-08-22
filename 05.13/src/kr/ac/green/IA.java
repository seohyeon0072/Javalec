package kr.ac.green;

/*
 * interface ( 1.7 )
 * -> 상수(static final)
 * -> 추상메서드(abstract)
 * 
 * interface ( 1.8 )
 * -> static method
 * -> default method
 */

@FunctionalInterface
public interface IA {
	void todo();
	
	static void print() {
		System.out.println("HELLO");
	}
	
	default void methodA() {
		System.out.println("A");
	}
	
	default void methodB() {
		System.out.println("B");
	}
}

interface IC {
	default void mothodA() {
		System.out.println("IC A");
	}
}

class B implements IA , IC{
	@Override
	public void todo() {
		IA.super.methodA();
	}
	
	@Override
	public void methodA() {
		System.out.println("A2");
	}
}

class Another {
	public void doti() {
		B b = new B();
		b.methodA();
	}
}