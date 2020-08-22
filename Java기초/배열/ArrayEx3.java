class ArrayEx3 {	
	public static void print(int n, int m){
			/* .... */
		}
	public static void print(int[] arr){
		for(int n : arr){
			System.out.println(n);
			}
		}
	// JDK1.5~
	// variable length arguments 가변인자
	// 배열을 만들어서 넣어야하는데 알아서 배열을 만들어줌

	// VALARG
	/*
		1.가변길이인자는 메서드당 1개만 사용할 수 있다.
		2.일반파라미터와 함께 쓸 수 있다.
		->일반 파라미터는 생략할 수 없다.
		->가변길이 인자는 마지막에 존재해야한다.
	*/
	
	public static void newPrint(int... nums){
		print(nums);
	}

	public static void todo(String str, int... nums){

	}
	//public 모든범위 사용
	//static 사용이유 호출하려면 main사용해야해서 미리 만들어져 있어야함
	//void 결과를 전송할수가 없는 시스템
	//main 이름
	//String[] 
	public static void main(String[] args)	{
		todo("a");
		todo("a", 1,2,3,4,5,6,7,8);

		int[] arr = {1,2,3};
		print(arr);

		newPrint(1);
		newPrint(1,2,3);
		newPrint(new int[]{1,2,3,4,5});
	}
}
