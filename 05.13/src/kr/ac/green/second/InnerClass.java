package kr.ac.green.second;

public class InnerClass {
	private int num =100;
	private InnerClass2 some = new InnerClass2();
	
	public void print() {
		System.out.println(some.other);
		some.print2();
	}
	class InnerClass2 {
		private int other = 10;
		public void print() {
			System.out.println(num);
		}
		
		private void print2() {
			System.out.println("zzzzzzzzzzzzzzzzz");
		}
	}
	
	
	public static void main(String[] args) {
		InnerClass l = new InnerClass();
		l.print();

	}

}
