import java.util.*;

class MyNums{
	private Integer[] nums;

	//자동 번호 뽑기
	public MyNums(){
		nums = autoSelect(6);
	}

	public MyNums(Integer... nums){
		Arrays.sort(nums);
		this.nums = nums;
	}
	
	//1~45까지 6개를 뽑아내는 메소드
	protected Integer[] autoSelect(int count){
		//랜덤 객체를 이용해서 난수를 뽑음
		Random r = new Random();
		HashSet<Integer> set = new HashSet<Integer>();
		
		//1~44까지나와서 +1해줌
		while(set.size() < count){
			// == (int)(Math.random() * 45) + 1
			set.add(r.nextInt(45) + 1);
		}
		Integer[] nums = set.toArray(new Integer[0]);
		//0~5번인덱스까지 정렬(6개뽑음)
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

//당첨번호
class LottoNums extends MyNums {
	// Bonut 인덱스 번호
	public static final int BONUS = 6;

	//자동 번호뽑기
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
		return super.toString() + "\n마지막 번호가 보너스 번호입니다.";
	}
}

class LottoGame {
	private Scanner scan; //출력
	private MyNums[] nums; //숫자 6개들어가있는거 배열로표현
	private LottoNums goal; //당첨번호
	
	public LottoGame(){
		scan = new Scanner(System.in);
		howManyPlay(); //몇개사요
		getGoal(); //당첨번호
		showResult(); //결과
	}
	
	private void howManyPlay(){
		System.out.print("몇개를 살까요? : " );
		int count = scan.nextInt();
		nums = new MyNums[count];
		for(int idx=0; idx<count; idx++){
			nums[idx] = new MyNums(); //파라미터 없는 생성자는 위에서 자동으로 생성해줌
			
			System.out.println(idx + 1 + "회 번호 : " + nums[idx]);
		}
	}
	
	//당첨번호 뽑기
	private void getGoal(){
		goal = new LottoNums();
		System.out.println("당첨번호를 추첨 합니다.");
		System.out.println("당첨번호 : " + goal);
	}
	
	//비교하는것
	private void showResult(){
		//배열을 복사한다.
		Integer[] excBonus = Arrays.copyOfRange(
			//0~5까지 6개만 들고옴. (마지막 번호가 보너스번호라서 6번인덱스꺼는 빼고 들고옴)
			goal.getNums(), 0, LottoNums.BONUS
		);
		
		//배열을 이용하여 Vector를 만든다.
		List<Integer> goalList = new Vector<Integer>(
			Arrays.asList(excBonus)
		);
		int no = 1;
		for(MyNums temp : nums){//사용자가 뽑은 게임 수
			//에러나서 새롭게 Vector로 만듬
			List<Integer> myList =
				new Vector<Integer>(Arrays.asList(temp.getNums()));
			/*
				temp -> [1,2,3,7,8,10] 

				myList -> [1,2,3,4,5,6]
				goalList -> [1,2,3,7,8,9] [10]

				=======>[1,2,3]
				비교하고 남은수에따라서 등수선정
			*/
			myList.retainAll(goalList);//보너스 번호 빼고 비교(내번호 당첨번호 비교)
			int count = myList.size();//교집합 남은것의 수
			int rank = 0; //등수
		
			switch(count){
				case 6 :
					rank = 1;
					break;
				case 5 :
					//5개 맞을경우 보너스 번호 비교
					//원래 선택햇던 temp안에 있는 번호중에 보너스 번호가 있습니까?
					//temp에 보너스번호가 있으면 인덱스가 존재해서 2등
					//0보다 작으면 보너스번호가 없음 3등
					int indexOfBonus = Arrays.binarySearch(
						temp.getNums(), goal.getBonusNum() //
					);
					//보너스 번호 존재여부 확인
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
				//일치하는게 0이면 꽝
				result ="꽝";
			}else {
				//1개라도 있으면 switch에서 보고 rank선정
				result = rank + "등";
			}
			System.out.print(no + "회 게임 결과 : " + result);
			System.out.println("\t일치번호 ->" + myList);
			no++;
		}

	}


}

class UseLottoGame2 {
	public static void main(String[] args)	{
		new LottoGame();

	}
}
