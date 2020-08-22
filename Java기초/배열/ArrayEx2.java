class CalcResult {
	private int sum;
	private int multi;

	public CalcResult(int sum, int multi){
		setSum(sum);
		setMulti(multi);
	}

	public int getSum(){
		return sum;
	}

	public void setSum(int sum){
		this.sum = sum;
	}

	public int getMulti(){
		return multi;
	}

	public void setMulti(int multi){
		this.multi = multi;
	}
}
class ArrayEx2 {
	public static void printSum(int n, int m){
		System.out.println(n + m);
	}
	/*
		파라미터를 배열로 지정한 경우
		->정수갯수에 대한 제한을 제거할 수 있다.
	*/
	public static void newPrintSum(int[] someArr){
		int sum = 0;
		for(int n : someArr){
			sum += n;
		}
		System.out.println(sum);
	}

	//다중리턴가능
	/*
		리턴으로 배열을 사용한 경우
		-> 0 or 1개 리턴 -> 의미상 여러개의 값을 리턴할 수 있다.
	*/
	public static int[] makeIntArr(int length){
		return new int[length];
	}
	
	/*
		두수를 입력받아서 합과 곱을 리턴
	*/
	public static int[] process(int n, int m){
		int[] result = {n+m, n*m};
		return result;
	}

	public static CalcResult newProcess(int n, int m){
		return new CalcResult(n+m, n*m);
	}

	public static final int SUM = 0;
	public static final int MUTI = 1;

	public static void main(String[] args) {

		System.out.println("두수를 입력받아서 배열로 사용한 경우1(추천)");
		CalcResult obj = newProcess(5, 7);
		System.out.println(obj.getSum());
		System.out.println(obj.getMulti());

		System.out.println("두수를 입력받아서 배열로 사용한 경우2");
		int[] result = process(5, 4);
		System.out.println(result[SUM]);
		System.out.println(result[MUTI]);

		System.out.println("리턴을 배열로 사용한 경우");
		int[] myArr = makeIntArr(3);
		myArr[0] = 4;
		myArr[1] = 2;
		myArr[2] = 100;

		for(int i = 0; i < myArr.length; i++){
			System.out.println(myArr[i]);
		}
		
		System.out.println("파라미터를 배열로 사용한 경우");
		int[] arr = {1,2,3,4,5,6,7};
		newPrintSum(arr);
		newPrintSum(new int[]{1,2,3});
		
	}
}
