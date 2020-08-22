package project.sixteen;

public class SynchVsNotCynch {
	private static final long CALL_COUNT = 100000000L;
	public static void main(String[] args) {
		trial(CALL_COUNT,new NotSynch());
		trial(CALL_COUNT,new Synch());
		
	}
	private static void trial(long count,Object obj){
		String msg = obj.toString(); 
		System.out.println(msg+" : BEGIN"); 
		long startTime = System.currentTimeMillis();
		for(int i =0;i<count;i++){
			obj.toString();
		} 
		System.out.println(
				"Elapsed time ="+  //경과시간
			    (System.currentTimeMillis()-startTime)+"ms");
		System.out.println(msg +": END");
	}
}
class Synch{
	private final String name="Synch";
	@Override
	public synchronized String toString(){
		return "["+name+"]";
	}
}
class NotSynch extends Synch{	
	private final String name="NotSynch";
	@Override
	public String toString(){
		return "["+name+"]";
	}
}