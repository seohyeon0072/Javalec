import java.util.*;

class MyNums{
	private Integer[] nums;

	//�ڵ� ��ȣ �̱�
	public MyNums(){
		nums = autoSelect(6);
	}

	public MyNums(Integer... nums){
		Arrays.sort(nums);
		this.nums = nums;
	}
	
	//1~45���� 6���� �̾Ƴ��� �޼ҵ�
	protected Integer[] autoSelect(int count){
		//���� ��ü�� �̿��ؼ� ������ ����
		Random r = new Random();
		HashSet<Integer> set = new HashSet<Integer>();
		
		//1~44�������ͼ� +1����
		while(set.size() < count){
			// == (int)(Math.random() * 45) + 1
			set.add(r.nextInt(45) + 1);
		}
		Integer[] nums = set.toArray(new Integer[0]);
		//0~5���ε������� ����(6������)
		Arrays.sort(nums, 0, 6);
		return nums;
	}

	public Integer[] getNums(){
		return nums;
	}

	public void setNums(Integer... nums){
		this.nums = nums;
	}
	
	@Override
	public String toString(){
		return Arrays.toString(nums);
	}
}

//��÷��ȣ
class LottoNums extends MyNums {
	// Bonut �ε��� ��ȣ
	public static final int BONUS = 6;

	//�ڵ� ��ȣ�̱�
	public LottoNums(){
		setNums(autoSelect(7));
	}

	public LottoNums(Integer... nums){
		super(nums);
	}
	
	public Integer getBonusNum(){
		return getNums()[BONUS];
	}

	@Override
	public String toString(){
		return super.toString() + "\n������ ��ȣ�� ���ʽ� ��ȣ�Դϴ�.";
	}
}

class LottoGame {
	private Scanner scan; //���
	private MyNums[] nums; //���� 6�����ִ°� �迭��ǥ��
	private LottoNums goal; //��÷��ȣ
	
	public LottoGame(){
		scan = new Scanner(System.in);
		howManyPlay(); //����
		getGoal(); //��÷��ȣ
		showResult(); //���
	}
	
	private void howManyPlay(){
		System.out.print("��� ����? : " );
		int count = scan.nextInt();
		nums = new MyNums[count];
		for(int idx=0; idx<count; idx++){
			nums[idx] = new MyNums(); //�Ķ���� ���� �����ڴ� ������ �ڵ����� ��������
			
			System.out.println(idx + 1 + "ȸ ��ȣ : " + nums[idx]);
		}
	}
	
	//��÷��ȣ �̱�
	private void getGoal(){
		goal = new LottoNums();
		System.out.println("��÷��ȣ�� ��÷ �մϴ�.");
		System.out.println("��÷��ȣ : " + goal);
	}
	
	//���ϴ°�
	private void showResult(){
		//�迭�� �����Ѵ�.
		Integer[] excBonus = Arrays.copyOfRange(
			//0~5���� 6���� ����. (������ ��ȣ�� ���ʽ���ȣ�� 6���ε������� ���� ����)
			goal.getNums(), 0, LottoNums.BONUS
		);
		
		//�迭�� �̿��Ͽ� Vector�� �����.
		List<Integer> goalList = new Vector<Integer>(
			Arrays.asList(excBonus)
		);
		int no = 1;
		for(MyNums temp : nums){//����ڰ� ���� ���� ��
			//�������� ���Ӱ� Vector�� ����
			List<Integer> myList =
				new Vector<Integer>(Arrays.asList(temp.getNums()));
			/*
				temp -> [1,2,3,7,8,10] 

				myList -> [1,2,3,4,5,6]
				goalList -> [1,2,3,7,8,9] [10]

				=======>[1,2,3]
				���ϰ� ������������ �������
			*/
			myList.retainAll(goalList);//���ʽ� ��ȣ ���� ��(����ȣ ��÷��ȣ ��)
			int count = myList.size();//������ �������� ��
			int rank = 0; //���
		
			switch(count){
				case 6 :
					rank = 1;
					break;
				case 5 :
					//5�� ������� ���ʽ� ��ȣ ��
					//���� �����޴� temp�ȿ� �ִ� ��ȣ�߿� ���ʽ� ��ȣ�� �ֽ��ϱ�?
					//temp�� ���ʽ���ȣ�� ������ �ε����� �����ؼ� 2��
					//0���� ������ ���ʽ���ȣ�� ���� 3��
					int indexOfBonus = Arrays.binarySearch(
						temp.getNums(), goal.getBonusNum() //
					);
					//���ʽ� ��ȣ ���翩�� Ȯ��
					if(indexOfBonus >= 0){
						rank = 2;
					} else {
						rank = 3;
					}
					break;
				case 4 :
					rank = 4;
					break;
				case 3 : 
					rank = 5;
					break;
			}
			String result;
			if(rank == 0){
				//��ġ�ϴ°� 0�̸� ��
				result ="��";
			}else {
				//1���� ������ switch���� ���� rank����
				result = rank + "��";
			}
			System.out.print(no + "ȸ ���� ��� : " + result);
			System.out.println("\t��ġ��ȣ ->" + myList);
			no++;
		}

	}


}

class UseLottoGame2 {
	public static void main(String[] args)	{
		new LottoGame();

	}
}
