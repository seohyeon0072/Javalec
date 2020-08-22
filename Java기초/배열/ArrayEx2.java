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
		�Ķ���͸� �迭�� ������ ���
		->���������� ���� ������ ������ �� �ִ�.
	*/
	public static void newPrintSum(int[] someArr){
		int sum = 0;
		for(int n : someArr){
			sum += n;
		}
		System.out.println(sum);
	}

	//���߸��ϰ���
	/*
		�������� �迭�� ����� ���
		-> 0 or 1�� ���� -> �ǹ̻� �������� ���� ������ �� �ִ�.
	*/
	public static int[] makeIntArr(int length){
		return new int[length];
	}
	
	/*
		�μ��� �Է¹޾Ƽ� �հ� ���� ����
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

		System.out.println("�μ��� �Է¹޾Ƽ� �迭�� ����� ���1(��õ)");
		CalcResult obj = newProcess(5, 7);
		System.out.println(obj.getSum());
		System.out.println(obj.getMulti());

		System.out.println("�μ��� �Է¹޾Ƽ� �迭�� ����� ���2");
		int[] result = process(5, 4);
		System.out.println(result[SUM]);
		System.out.println(result[MUTI]);

		System.out.println("������ �迭�� ����� ���");
		int[] myArr = makeIntArr(3);
		myArr[0] = 4;
		myArr[1] = 2;
		myArr[2] = 100;

		for(int i = 0; i < myArr.length; i++){
			System.out.println(myArr[i]);
		}
		
		System.out.println("�Ķ���͸� �迭�� ����� ���");
		int[] arr = {1,2,3,4,5,6,7};
		newPrintSum(arr);
		newPrintSum(new int[]{1,2,3});
		
	}
}
