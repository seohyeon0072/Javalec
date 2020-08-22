package kr.ac.green.another;

public class Human {
	private ISpeakable sp;

	public Human(ISpeakable sp) {
		this.sp = sp;
	}

	public void performSpeak() {
		sp.speak();
	}

	public static void main(String[] args) {
		Human man = new Human(new ISpeakable() {
			@Override
			public void speak() {
				System.out.println("Ä¼Ä¼");
			}
		});
		man.performSpeak();
		
		//ÇÏ´Â ÀÏÀÌ ÇÑ ÁÙÀÌ¸é Áß°ýÈ£ ¾È ÃÄµµ µÊ..
		Human man2 = new Human( ()-> System.out.println("bye") );
		man2.performSpeak();
	}
}
