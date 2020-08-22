package Lec1;

public class Producer extends Thread{
	private MyBox box; 
	public Producer(MyBox box) {
		this.box=box; 
	}
	public void run(){ 
		for(int i=0;i<20;i++){
			box.put(i); //0¿ª ∫∏≥ø -> false
			try {
				sleep(100); //¿·µÎ
			} catch (InterruptedException e) {
			}
		}
	}
}
