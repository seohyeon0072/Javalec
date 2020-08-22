package kr.ac.green.another;

/*
 * 1. 외부클래스 객체 생성과 관계없이 생성 가능
 */
public class Some {
	//static 붙으면 static 클래스에서 사용 가능함.
	private int num = 100;
	
	public void call() {
		new Other().print();
		
		Other.todo();
	}
	
	// 3 번째 형태
	// 외부 클래스가 만들어져야 이너 클래스를 사용할 수 있다는 규칙이 깨짐...
	// 잘 안씀 ... 있따 .....
	
	static class Other {
		// 외부 클래스 객체 생성과 관계없이 객체 생성이 가능.
		public void print() {
			//System.out.println(num);
		}
		
		public static void todo() {
			System.out.println();
		}
		

	}
	
	public static void main(String[] args) {
		Some.Other o = new Some.Other();
	}
}
