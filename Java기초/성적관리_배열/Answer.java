class Subject{
	private String name;
	private int score;

	//교과목 찾을때 사용
	// = public boolean equals(Object o)
	//특정과목을 찾을때
	//검색용
	public Subject(String name){
		setName(name);
	}
	
	//어떤과목 몇점을 받았다고 확인용
	public Subject(String name, int score){
		setName(name);
		setScore(score);
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getScore(){
		return score;
	}

	public void setScore(int score){
		this.score = score;
	}

	@Override 
	public String toString(){
		return name + " : " + score;
	}

	//이름이 같으면 같은 교과목임.
	@Override
	public boolean equals(Object o){
		if(o == null || !(o instanceof Subject)){
			return false;
		}
		Subject temp = (Subject)o;
		//교과목들이 여러가지 있을때 원하는 과목을 찾기위해서
		return name.equals(temp.getName());
	}
}

class Student{
	private String name; //학생 이름
	private Subject[] scores; //여러교과목의 점수를 가질수 있기때문에 배열

	//가변형 배열
	public Student(String name, Subject... scores){
		setName(name);
		setScores(scores);
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Subject[] getScores(){
		return scores;
	}

	public void setScores(Subject... scores){
		this.scores = scores;
	}
	//개인별 총점
	public int getTotal(){
		int total = 0;
		//Subject에있는 개인의 가변형배열의 점수들을 다 불러와서 더해줌.
		//scores Subject배열에있는것을 하나씩 불러와서 합을 구함
		for(Subject temp : scores){
			total += temp.getScore();
		}
		return total;
	}

	//개인별 평균
	//개인별 총점에서 / 과목개수만큼(과목이 변할수도 있음) 나눠서 평균을 구함
	public int getAvg(){
		return getTotal() / scores.length;
	}

	/*
		교과목 점수 찾기
		param : 교과목 이름
		return : 점수. 단, 존재하지 않는 교과목인 경우 -1
	*/

	//특정교과목의 이름을 던져주면 교과목의 점수를 리턴함
	//-1을 설정한 이유는 제일 낮은점수가 0점이라서 -1
	//내가원하는 교과목의 점수를 가져옴
	public int getSubjectScore(String name){
		//교과목이름을 받아서 Subject객체 생성
		Subject goal = new Subject(name); //수학이면
		int score = -1;
		for(Subject temp : scores){ //이름이 같은 교과목을 찾아서 점수를 리턴
			if(temp.equals(goal)){ //temp가 수학을 가지고 있는지 없는지 확인
				score = temp.getScore(); //만약 있다면 점수를 넣고 리턴
			}
		}
		return score;
	}
	
	//이름 출력
	//점수 하나씩 불러와서 출력
	//총점 출력
	//평균 출력
	@Override
	public String toString(){
		String info ="<<" + name + ">>\n";
		for(Subject temp : scores){
			info += temp.toString() + "\n";
		}
		info += "총점 : " + getTotal() + "\n";
		info += "평균 : " + getAvg();

		return info;
	}
}

//학급
//교과목별로 구분하기위해서 학생
class Klass {

		private Student[] list;

		public Klass(Student... list){
			setList(list);
		}
		public Student[] getList(){
			return list;
		}
		
		public void setList(Student... list){
			this.list = list;
		}
		
		//교과목 총점
		public int getTotal(String subjectName){ //교과목이름을 받고
			int total = 0;
			for(Student temp : list){//list는 학생목록(학생 한명씩 빼와서 Student에
				int score = temp.getSubjectScore(subjectName);
				if(score >= 0){ //위에 -1을 준이유. 실제로 존재하면 점수라면 누적을 시켜라
					total += score;
				}
			}
			return total;
		}
			
		//교과목 평균
		public int getAvg(String subjectName){ //교과목이름 받아서 합구하고 학생수만큼 나눠줌
			return getTotal(subjectName) / list.length;
		}

		@Override
		public String toString(){
			String info ="- 학급정보 -\n";
			for(Student temp : list){
				info += temp.toString() + "\n";
			} 
			//같은 학급의 학생은 시험 교과가 같으므로...
			//list[0]는 첫번째 학생의 Subject정보
			Subject[] scores = list[0].getScores();
			for(Subject temp : scores){
				String name = temp.getName(); //Subject이름필요함
				info += name + " 총점 : " + getTotal(name) + "\n";
				info += name + "평균 : " + getAvg(name) + "\n";
			}
			return info;
		}	
}

class Answer{
	public static void main(String[] args)	{
		Subject s1 = new Subject("국어", 90);
		Subject s2 = new Subject("영어", 90);
		Subject s3 = new Subject("수학", 90);

		Student st1 = new Student("애봉이",s1,s2,s3);
		Student st2 = new Student(
			"김수현",
			new Subject("국어",90),
			new Subject("영어",90),
			new Subject("수학",90)
		);
		Student st3 = new Student(
			"김유정",
			new Subject("국어",100),
			new Subject("영어",100),
			new Subject("수학",100)
			);

		System.out.println(new Klass(st1,st2,st3));
	}
}