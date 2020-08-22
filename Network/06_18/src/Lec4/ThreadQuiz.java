package Lec4;

public class ThreadQuiz {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t = new Thread(){
			@Override
			public void run(){
					boolean flag =true; 
					while(flag){//true가 되면 나가기
						flag = Thread.interrupted();  
						System.out.println(flag); //true
						if(isInterrupted()){
							flag=false;
						}
						System.out.println("loop");
					}
					System.out.println("hi~");
				}
			 
		};
		t.start();
		
//		t.interrupt();//false
	}

}
